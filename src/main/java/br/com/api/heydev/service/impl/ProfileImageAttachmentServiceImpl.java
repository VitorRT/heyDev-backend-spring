package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.ProfileEntity;
import br.com.api.heydev.database.entity.ProfileImageAttachmentEntity;
import br.com.api.heydev.database.repository.IProfileImageAttachmentRepository;
import br.com.api.heydev.database.repository.IProfileRepository;
import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageAttachmentResponse;
import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageResourceResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.handler.exception.FileNotAnImageException;
import br.com.api.heydev.handler.exception.FileUploadException;
import br.com.api.heydev.service.IProfileImageAttachmentService;
import br.com.api.heydev.utils.FileFinder;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProfileImageAttachmentServiceImpl implements IProfileImageAttachmentService {
    private IProfileImageAttachmentRepository imageAttachmentRepository;
    private IProfileRepository profileRepository;
    private FileFinder fileFinder;
    private static final Map<String, String> EXTENSION_TO_MEDIA_TYPE_MAP = new HashMap<>();

    static {
        EXTENSION_TO_MEDIA_TYPE_MAP.put("jpg", "image/jpeg");
        EXTENSION_TO_MEDIA_TYPE_MAP.put("jpeg", "image/jpeg");
        EXTENSION_TO_MEDIA_TYPE_MAP.put("png", "image/png");
        EXTENSION_TO_MEDIA_TYPE_MAP.put("gif", "image/gif");
    }

    public ProfileImageAttachmentServiceImpl(
            IProfileImageAttachmentRepository imageAttachmentRepository,
            IProfileRepository profileRepository,
            FileFinder fileFinder) {
        this.imageAttachmentRepository = imageAttachmentRepository;
        this.profileRepository = profileRepository;
        this.fileFinder = fileFinder;
    }

    @Override
    public ProfileImageAttachmentResponse addProfileImage(UUID accountId) {
        String fileName = "unknow_user.jpg";
        ProfileEntity profile = getUserProfileByAccountId(accountId);
        ProfileImageAttachmentEntity profileAttachment = new ProfileImageAttachmentEntity();
        profileAttachment.setProfile(profile);
        try {
            byte[] defaultImageBytes = fileFinder.getFileByName(fileName);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userprofile/image/download/")
                    .path(accountId.toString())
                    .toUriString();

            String fileShowUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userprofile/image/show/")
                    .path(accountId.toString())
                    .toUriString();


            profileAttachment.setImageName(fileName);
            profileAttachment.setAttachment(defaultImageBytes);
            profileAttachment.setIsDefault(true);
            ProfileImageAttachmentEntity profileImagePersisted = imageAttachmentRepository.saveAndFlush(profileAttachment);

            ProfileImageAttachmentResponse response = new ProfileImageAttachmentResponse(
                    profileImagePersisted.getImageName(),
                    fileShowUri,
                    fileDownloadUri);
            return response;
        } catch (IOException e) {
            throw new FileUploadException();
        }
    }

    @Override
    public ProfileImageResourceResponse serveProfileImage(UUID accountId) {
        ProfileEntity profile = getUserProfileByAccountId(accountId);
        Optional<ProfileImageAttachmentEntity> optional = imageAttachmentRepository.findAttachmentByProfileId(profile.getProfileId());
        if(!optional.isPresent()) {
            throw new EntityNotFoundException(InternalTypeErrorCodesEnum.E410014.getCode());
        }

        byte[] attachmentBytes = optional.get().getAttachment();
        String imageName =  optional.get().getImageName();

        Resource resource = new ByteArrayResource(attachmentBytes, imageName);
        ProfileImageResourceResponse response = new ProfileImageResourceResponse(imageName, resource);
        return response;
    }

    public String getContentType(Resource file, String fileName) {
        if (StringUtils.hasText(fileName)) {
            String extension = StringUtils.getFilenameExtension(fileName);
            if (extension != null) {
                String mediaType = EXTENSION_TO_MEDIA_TYPE_MAP.getOrDefault(extension.toLowerCase(), "application/octet-stream");
                return mediaType;
            }
        }
        return "application/octet-stream";
    }

    @Override
    public ProfileImageAttachmentResponse updateProfileImage(UUID accountId, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ProfileEntity profile = getUserProfileByAccountId(accountId);
        ProfileImageAttachmentEntity imageProfile = getProfileImageByProfileId(profile.getProfileId());

        try {
            if(ImageIO.read(file.getInputStream()) == null) throw new FileNotAnImageException();

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userprofile/image/download/")
                    .path(accountId.toString())
                    .toUriString();

            String fileShowUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userprofile/image/show/")
                    .path(accountId.toString())
                    .toUriString();


            imageProfile.setImageName(fileName);
            imageProfile.setAttachment(file.getBytes());
            imageProfile.setIsDefault(false);
            ProfileImageAttachmentEntity profileImagePersisted = imageAttachmentRepository.saveAndFlush(imageProfile);

            log.info("[ DB Persisted ] Successfully updated profile image of the profile: " + profile.getProfileId());

            ProfileImageAttachmentResponse response = new ProfileImageAttachmentResponse(
                    profileImagePersisted.getImageName(),
                    fileShowUri,
                    fileDownloadUri);
            return response;
        } catch (IOException e) {
            throw new FileUploadException();
        }
    }

    @Override
    public ProfileImageAttachmentResponse removeProfileImage(UUID accountId) {
        String fileName = "unknow_user.jpg";
        ProfileEntity profile = getUserProfileByAccountId(accountId);
        ProfileImageAttachmentEntity imageProfile = getProfileImageByProfileId(profile.getProfileId());
        imageProfile.setProfile(profile);
        imageProfile.setImageName(fileName);
        try {
            byte[] defaultImageBytes = fileFinder.getFileByName(fileName);
            imageProfile.setAttachment(defaultImageBytes);
            imageProfile.setIsDefault(true);
            ProfileImageAttachmentEntity profileImagePersisted = imageAttachmentRepository.saveAndFlush(imageProfile);


            log.info("[ DB Persisted ] Successfully removed profile image of the profile: " + profile.getProfileId());

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userprofile/image/download/")
                    .path(accountId.toString())
                    .toUriString();

            String fileShowUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userprofile/image/show/")
                    .path(accountId.toString())
                    .toUriString();

            ProfileImageAttachmentResponse response = new ProfileImageAttachmentResponse(
                    profileImagePersisted.getImageName(),
                    fileShowUri,
                    fileDownloadUri);
            return response;
        } catch(IOException e) {
            throw new FileUploadException();
        }


    }

    private ProfileEntity getUserProfileByAccountId(UUID accountId) {
       return profileRepository.findByAccountId(accountId).orElseThrow(
               () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410014.getCode())
       );
    }

    private ProfileImageAttachmentEntity getProfileImageByProfileId(UUID profileId) {
        return imageAttachmentRepository.findAttachmentByProfileId(profileId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410014.getCode())
        );
    }
}

package br.com.api.heydev.service;

import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageAttachmentResponse;
import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageResourceResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IProfileImageAttachmentService {
    ProfileImageAttachmentResponse addProfileImage(UUID accountId);
    ProfileImageResourceResponse serveProfileImage(UUID accountId);
    String getContentType(Resource file, String fileName);
    ProfileImageAttachmentResponse updateProfileImage(UUID accountId, MultipartFile file);

    ProfileImageAttachmentResponse removeProfileImage(UUID accountId);
}

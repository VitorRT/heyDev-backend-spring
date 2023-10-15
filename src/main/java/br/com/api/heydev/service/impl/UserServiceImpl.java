package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.ProfileEntity;
import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.IProfileImageAttachmentRepository;
import br.com.api.heydev.database.repository.IProfileRepository;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.request.account.AccountPostRequest;
import br.com.api.heydev.dto.response.account.AccountResponse;
import br.com.api.heydev.dto.response.account.admin.AdminUserResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.enums.ProfileRole;
import br.com.api.heydev.enums.UserRole;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import br.com.api.heydev.service.IProfileImageAttachmentService;
import br.com.api.heydev.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;
    private IProfileRepository profileRepository;
    private IProfileImageAttachmentRepository imageAttachmentRepository;
    private IProfileImageAttachmentService profileImageAttachmentService;

    private PasswordEncoder encoder;

    @Deprecated
    public UserServiceImpl(
            IUserRepository userRepository,
            IProfileRepository profileRepository,
            IProfileImageAttachmentRepository imageAttachmentRepository,
            IProfileImageAttachmentService profileImageAttachmentService,
            PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.imageAttachmentRepository = imageAttachmentRepository;
        this.profileImageAttachmentService = profileImageAttachmentService;
        this.encoder = encoder;
    }

    @Override
    public AccountResponse createAccount(AccountPostRequest request)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        verifyUsernameAlreadyExist(request.username());
        verifyEmailAlreadyExist(request.email());
        UserEntity userEntity = toEntity(request);

        UserEntity userPersisted = this.userRepository.saveAndFlush(userEntity);

        ProfileEntity profile = toProfileEntity(request);
        profile.setUser(userPersisted);

        profileRepository.saveAndFlush(profile);

        profileImageAttachmentService.addProfileImage(userPersisted.getUserId());

        log.info("[ DB Persisted ] - Account successfully persisted!");
        return toResponse(userPersisted);
    }

    @Override
    public List<AdminUserResponse> adminlistAllAccounts() {
        List<AdminUserResponse> users =this.userRepository.findAll().stream().map(AdminUserResponse::new).toList();

        log.info("[ DB Query ] - Selection in all user performed successfully!");
        return users;
    }

    @Override
    public AccountResponse updateUsername(UUID userId, String username) throws UsernameAlreadyExistsException  {
        UserEntity userEntity = getUserById(userId);

        if(userEntity.getUsernameAccount().equals(username)) {
            log.info("[ DB Persisted ] - Username successfully edited!");
            return toResponse(userEntity);
        }

        verifyUsernameAlreadyExist(username);
        userEntity.setUsernameAccount(username);
        UserEntity userPersisted = this.userRepository.saveAndFlush(userEntity);

        log.info("[ DB Persisted ] - Username successfully edited!");
        return toResponse(userPersisted);
    }

    @Override
    public void deleteAccountById(UUID userId) {
        UserEntity userEntity = getUserById(userId);
        this.userRepository.delete(userEntity);

        log.info("[ DB Delete ] - account successfully deleted in {}.", LocalDateTime.now());
    }

    @Override
    public AccountResponse detailsAccountById(UUID userId) {
        UserEntity userEntity = getUserById(userId);

        log.info("[ DB Query ] - Selection in the user performed successfully!");
        return toResponse(userEntity);
    }

    private UserEntity toEntity(AccountPostRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsernameAccount(request.username());
        userEntity.setEmail(request.email());
        userEntity.setPassword(encoder.encode(request.password()));
        userEntity.setRole(UserRole.ROLE_USER);
        return userEntity;
    }

    private AccountResponse toResponse(UserEntity userEntity) {
        return new AccountResponse(userEntity);
    }

    private ProfileEntity toProfileEntity(AccountPostRequest request) {
        ProfileEntity profile = new ProfileEntity();
        profile.setProfileRole(ProfileRole.ROLE_USER);
        profile.setSocialName(request.socialName());
        profile.setGithubConnected(false);
        return profile;
    }

    private void verifyUsernameAlreadyExist(String username) throws UsernameAlreadyExistsException {
        if(this.userRepository.existsByUsernameAccount(username)) {
            throw new UsernameAlreadyExistsException();
        }
    }

    private void verifyEmailAlreadyExist(String email) throws EmailAlreadyExistsException {
        if(this.userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

    private UserEntity getUserById(UUID userId)  {
        if(!this.userRepository.existsByUserId(userId)) {
            throw new EntityNotFoundException(InternalTypeErrorCodesEnum.E410000.getCode());
        }
        return this.userRepository.findById(userId).get();
    }

}

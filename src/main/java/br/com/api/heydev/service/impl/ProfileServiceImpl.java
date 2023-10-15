package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.ProfileEntity;
import br.com.api.heydev.database.repository.IProfileImageAttachmentRepository;
import br.com.api.heydev.database.repository.IProfileRepository;
import br.com.api.heydev.dto.response.profile.ProfileResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.service.IProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProfileServiceImpl implements IProfileService {
    private IProfileRepository profileRepository;

    @Deprecated
    public ProfileServiceImpl(IProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileResponse detailsProfileByAccountId(UUID accountId) {
        ProfileEntity profile = getProfileByAccountId(accountId);

        log.info("[ DB Query ] Details in profile. [ account_id: " + accountId +" ]");
        return new ProfileResponse(profile);
    }

    @Override
    public ProfileResponse updateSocialNameByAccountId(UUID accountId, String socialName) {
        ProfileEntity profile = getProfileByAccountId(accountId);
        profile.setSocialName(socialName);

        ProfileEntity profilePersisted = profileRepository.saveAndFlush(profile);
        log.info("[ DB Persist ] Social name updated. [ account_id: " + accountId +" ]");
        return new ProfileResponse(profilePersisted);
    }

    private ProfileEntity getProfileByAccountId(UUID accountId) {
        ProfileEntity profile = profileRepository.findByAccountId(accountId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410000.getCode())
        );

        return profile;
    }
}

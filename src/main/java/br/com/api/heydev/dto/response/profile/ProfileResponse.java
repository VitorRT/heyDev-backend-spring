package br.com.api.heydev.dto.response.profile;

import br.com.api.heydev.database.entity.ProfileEntity;
import br.com.api.heydev.dto.response.account.AccountResponse;

import java.util.UUID;

public record ProfileResponse(
        UUID profileId,
        AccountResponse user,
        String socialName
) {
    public ProfileResponse(ProfileEntity entity) {
        this(
          entity.getProfileId(),
          new AccountResponse(entity.getUser()),
          entity.getSocialName()
        );
    }
}

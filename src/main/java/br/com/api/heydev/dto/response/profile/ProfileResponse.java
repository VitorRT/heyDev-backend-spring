package br.com.api.heydev.dto.response.profile;

import br.com.api.heydev.database.entity.ProfileEntity;
import br.com.api.heydev.dto.response.account.AccountResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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

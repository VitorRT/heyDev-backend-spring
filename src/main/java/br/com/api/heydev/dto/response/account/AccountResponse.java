package br.com.api.heydev.dto.response.account;

import br.com.api.heydev.database.entity.UserEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record AccountResponse(UUID accountId, String username) {
    public AccountResponse(UserEntity userEntity) {
        this(
                userEntity.getUserId(),
                userEntity.getUsernameAccount()
        );
    }
}

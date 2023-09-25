package br.com.api.heydev.dto.response.account;

import br.com.api.heydev.database.entity.UserEntity;

import java.util.UUID;

public record AccountResponse(UUID accountId, String username) {
    public AccountResponse(UserEntity userEntity) {
        this(
                userEntity.getUserId(),
                userEntity.getUsername()
        );
    }
}

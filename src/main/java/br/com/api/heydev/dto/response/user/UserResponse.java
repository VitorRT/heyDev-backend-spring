package br.com.api.heydev.dto.response.user;

import br.com.api.heydev.database.entity.UserEntity;

import java.util.UUID;

public record UserResponse(UUID userId, String username) {
    public UserResponse(UserEntity userEntity) {
        this(
                userEntity.getUserId(),
                userEntity.getUsername()
        );
    }
}

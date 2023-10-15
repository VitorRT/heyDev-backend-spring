package br.com.api.heydev.dto.response.account.admin;

import br.com.api.heydev.database.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdminUserResponse(
        UUID userId,
        String username,
        String email,
        Boolean githubConnected,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public AdminUserResponse(UserEntity userEntity) {
        this(
                userEntity.getUserId(),
                userEntity.getUsernameAccount(),
                userEntity.getEmail(),
                userEntity.getGithubConnected(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }
}

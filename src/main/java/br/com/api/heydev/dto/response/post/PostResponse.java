package br.com.api.heydev.dto.response.post;

import br.com.api.heydev.database.entity.PostEntity;
import br.com.api.heydev.dto.response.account.AccountResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record PostResponse(
        UUID postId,
        String content,
        AccountResponse userAccount,
        LocalDateTime createdAt
) {
    public PostResponse(PostEntity entity) {
        this(
                entity.getPostId(),
                entity.getContent(),
                new AccountResponse(entity.getUser()),
                entity.getCreatedAt()
        );
    }
}

package br.com.api.heydev.dto.response.comment;

import br.com.api.heydev.database.entity.CommentEntity;
import br.com.api.heydev.dto.response.account.AccountResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record CommentResponse(
        UUID commentId,
        AccountResponse user,
        String content,
        LocalDateTime createdAt,
        Boolean edited
) {
    public CommentResponse(CommentEntity entity) {
        this(
                entity.getCommentId(),
                new AccountResponse(entity.getUser()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getEdited()
        );
    }
}

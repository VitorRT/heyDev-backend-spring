package br.com.api.heydev.dto.response.comment;

import br.com.api.heydev.database.entity.CommentEntity;
import br.com.api.heydev.dto.response.account.AccountResponse;

import java.time.LocalDateTime;
import java.util.UUID;

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

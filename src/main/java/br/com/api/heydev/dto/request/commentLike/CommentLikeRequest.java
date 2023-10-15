package br.com.api.heydev.dto.request.commentLike;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentLikeRequest(
        @NotNull(message = "410.018")
        UUID userAccountId,
        @NotNull(message = "410.019")
        UUID commentId
) {
}

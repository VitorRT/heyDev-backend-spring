package br.com.api.heydev.dto.request.postLike;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record PostLikeRequest(
        @NotNull(message = "410.018")
        UUID userAccountId,
        @NotNull(message = "410.021")
        UUID postId
) {
}

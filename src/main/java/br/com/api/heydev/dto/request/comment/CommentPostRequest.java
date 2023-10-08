package br.com.api.heydev.dto.request.comment;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record CommentPostRequest(
        @NotNull(message = "410.018")
        UUID userAccountId,
        @NotNull(message = "410.021")
        UUID postId,
        @NotBlank(message = "410.020")
        String content
) {
}

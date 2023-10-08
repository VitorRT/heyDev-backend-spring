package br.com.api.heydev.dto.request.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest(
        @NotBlank(message = "410.020")
        String content
) {
}

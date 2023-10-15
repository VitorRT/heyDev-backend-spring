package br.com.api.heydev.dto.request.post;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
        @NotBlank(message = "410.017")
        String content
) {
}

package br.com.api.heydev.dto.request.post;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record PostRequest(
        @NotNull(message = "410.018")
        UUID userAccountId,
        @NotBlank(message = "410.017")
        String content
) {
}

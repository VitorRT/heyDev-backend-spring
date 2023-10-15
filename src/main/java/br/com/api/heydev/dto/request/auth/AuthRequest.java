package br.com.api.heydev.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "410.005")
        @Email(message = "410.007")
        String email,

        @NotBlank(message = "410.006")
        String password
) {
}

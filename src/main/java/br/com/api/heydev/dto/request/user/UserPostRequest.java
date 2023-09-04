package br.com.api.heydev.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPostRequest(
        @NotBlank(message = "410.004")
        @Size(
                min = 3, max = 18,
                message = "410.008"
        )
        String username,
        @NotBlank(message = "410.005")
        @Email(message = "410.007")
        String email,
        @NotBlank(message = "410.006")
        @Size(min = 8, message = "410.009")
        String password
) { }

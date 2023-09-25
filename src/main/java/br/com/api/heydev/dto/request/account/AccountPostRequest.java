package br.com.api.heydev.dto.request.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record AccountPostRequest(
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
        String password,
        @NotBlank(message = "410.010")
        @Size(
                min = 4, max = 40,
                message = "410.011"
        )
        String socialName
) { }

package br.com.api.heydev.dto.response.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record SuccessAuth(
        String token,
        String tokenType,
        String prefix,
        UUID accountId
) {
}

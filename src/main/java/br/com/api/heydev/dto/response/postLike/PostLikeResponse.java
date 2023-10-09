package br.com.api.heydev.dto.response.postLike;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record PostLikeResponse(UUID likeId) {
}

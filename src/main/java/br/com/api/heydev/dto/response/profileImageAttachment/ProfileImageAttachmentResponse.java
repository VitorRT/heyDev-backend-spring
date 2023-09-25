package br.com.api.heydev.dto.response.profileImageAttachment;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record ProfileImageAttachmentResponse(
        String imageName,
        String showUri,
        String downloadUri
) {
}

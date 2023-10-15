package br.com.api.heydev.dto.response.profileImageAttachment;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.core.io.Resource;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record ProfileImageResourceResponse(
        String fileName,
        Resource resource
) {
}

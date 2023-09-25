package br.com.api.heydev.dto.response.profileImageAttachment;

import org.springframework.core.io.Resource;

public record ProfileImageResourceResponse(
        String fileName,
        Resource resource
) {
}

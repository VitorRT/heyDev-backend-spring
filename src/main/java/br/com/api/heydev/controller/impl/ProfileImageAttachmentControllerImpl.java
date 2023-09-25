package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IProfileImageAttachmentController;
import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageAttachmentResponse;
import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageResourceResponse;
import br.com.api.heydev.service.IProfileImageAttachmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/userprofile/image")
@Tag(name = "User Profile Image 📸")
public class ProfileImageAttachmentControllerImpl implements IProfileImageAttachmentController {
    private IProfileImageAttachmentService service;

    public ProfileImageAttachmentControllerImpl(IProfileImageAttachmentService service) {
        this.service = service;
    }

    @GetMapping(value = "/download/{accountId}")
    public ResponseEntity<Resource> downloadImage(UUID accountId, HttpServletRequest request) {
        ProfileImageResourceResponse imageFile = service.serveProfileImage(accountId);
        Resource resource = imageFile.resource();
        String contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageFile.fileName() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/show/{accountId}")
    public ResponseEntity<Resource> showImage(UUID accountId, HttpServletRequest request) {
        ProfileImageResourceResponse imageFile = service.serveProfileImage(accountId);
        Resource resource = imageFile.resource();
        String contentType = service.getContentType(resource, imageFile.fileName());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageFile.fileName() + "\"")
                .body(resource);
    }

    @PatchMapping(value = "/update/{accountId}")
    public ResponseEntity<ProfileImageAttachmentResponse> updateProfileImage(UUID accountId, MultipartFile file) {
        return ResponseEntity.ok(service.updateProfileImage(accountId, file));
    }

    @PatchMapping(value = "/remove/{accountId}")
    public ResponseEntity<ProfileImageAttachmentResponse> removeProfileImage(UUID accountId) {
        return ResponseEntity.ok(service.removeProfileImage(accountId));
    }
}

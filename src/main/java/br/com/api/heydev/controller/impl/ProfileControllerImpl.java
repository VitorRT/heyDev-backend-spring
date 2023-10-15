package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IProfileController;
import br.com.api.heydev.dto.response.profile.ProfileResponse;
import br.com.api.heydev.service.IProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/userprofile")
@Tag(name = "User Profile 👨🏽‍🚀")
public class ProfileControllerImpl implements IProfileController {
    private IProfileService service;

    @Deprecated
    public ProfileControllerImpl(IProfileService service) {
        this.service = service;
    }

    @GetMapping(value = "/details/{accountId}")
    public ResponseEntity<ProfileResponse> detailsProfileByAccountId(UUID accountId) {
        return ResponseEntity.ok(service.detailsProfileByAccountId(accountId));
    }

    @PutMapping(value = "/update/{accountId}")
    public ResponseEntity<ProfileResponse> updateSocialNameByAccountId(UUID accountId, String socialName) {
        return ResponseEntity.ok(service.updateSocialNameByAccountId(accountId, socialName));
    }
}

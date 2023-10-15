package br.com.api.heydev.service;

import br.com.api.heydev.dto.response.profile.ProfileResponse;

import java.util.UUID;

public interface IProfileService {
    ProfileResponse detailsProfileByAccountId(UUID accountId);
    ProfileResponse updateSocialNameByAccountId(UUID accountId, String socialName);
}

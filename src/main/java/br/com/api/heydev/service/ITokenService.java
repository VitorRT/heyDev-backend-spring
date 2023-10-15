package br.com.api.heydev.service;

import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.dto.request.auth.AuthRequest;
import br.com.api.heydev.dto.response.auth.SuccessAuth;

public interface ITokenService {
    SuccessAuth generateToken(AuthRequest request);
    UserEntity getUserEntityByToken(String token);
}

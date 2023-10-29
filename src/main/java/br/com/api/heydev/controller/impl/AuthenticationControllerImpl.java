package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IAuthenticationController;
import br.com.api.heydev.dto.request.auth.AuthRequest;
import br.com.api.heydev.dto.response.auth.SuccessAuth;
import br.com.api.heydev.service.ITokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication 🔏")
@CrossOrigin(origins = "http:localhost:3000")
public class AuthenticationControllerImpl implements IAuthenticationController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ITokenService tokenService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SuccessAuth> login(AuthRequest request) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = authManager.authenticate(usernamePassword);
        var authSuccessResponse = tokenService.generateToken(request);

        return ResponseEntity.ok(authSuccessResponse);
    }
}

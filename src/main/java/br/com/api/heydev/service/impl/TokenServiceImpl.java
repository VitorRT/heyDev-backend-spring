package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.request.auth.AuthRequest;
import br.com.api.heydev.dto.response.auth.SuccessAuth;
import br.com.api.heydev.service.ITokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private IUserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    private String JWT_ISSUER = "HeyDev!";

    @Override
    public SuccessAuth generateToken(AuthRequest request) {
        UserEntity userEntity = userRepository.findByEmail(request.email()).get();

        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(userEntity.getEmail())
                .withIssuer(JWT_ISSUER)
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(alg);

        var response = new SuccessAuth(token, "JWT", "Bearer ", userEntity.getUserId());
        return response;
    }

    @Override
    public UserEntity getUserEntityByToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer(JWT_ISSUER)
                .build()
                .verify(token)
                .getSubject();

        var response = userRepository.findByEmail(email).get();
        return response;
    }
}

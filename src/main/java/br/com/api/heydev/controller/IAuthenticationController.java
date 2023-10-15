package br.com.api.heydev.controller;

import br.com.api.heydev.dto.request.auth.AuthRequest;
import br.com.api.heydev.dto.response.auth.SuccessAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationController {

    @Operation(
            summary = "Autenticar no sistema da hey dev.",
            description = "Autenticação no sistema da hey dev."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Autenticado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<SuccessAuth> login(@RequestBody @Valid AuthRequest request);
}

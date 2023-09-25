package br.com.api.heydev.controller;

import br.com.api.heydev.dto.response.profile.ProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface IProfileController {

    @Operation(
            summary = "Detalhe de perfil do usuário por id da conta.",
            description = "Detalhamento dos dados do perfil do usuário por id da conta."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<ProfileResponse> detailsProfileByAccountId(@PathVariable(name = "accountId") UUID accountId);

    @Operation(
            summary = "Atualiza o nome social do perfil do usuário por id da conta.",
            description = "Atualização do nome social do perfil do usuário por id da conta."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Nome social atualizado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<ProfileResponse> updateSocialNameByAccountId(
            @PathVariable(name = "accountId") UUID accountId,
            @RequestParam(name = "socialName", required = true) String socialName
    );

}

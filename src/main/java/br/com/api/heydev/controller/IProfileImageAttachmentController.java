package br.com.api.heydev.controller;

import br.com.api.heydev.dto.response.profileImageAttachment.ProfileImageAttachmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IProfileImageAttachmentController {

    @Operation(
            summary = "Baixar imagem de perfil por id da conta.",
            description = "Download da imagem de perfil de um usuário passando o id da conta como path variable."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Imagem baixada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<Resource> downloadImage(
            @PathVariable(name = "accountId") UUID accountId,
            HttpServletRequest request);

    @Operation(
            summary = "Visualar imagem de perfil por id da conta.",
            description = "Visualização da imagem de perfil de um usuário passando o id da conta como path variable."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<Resource> showImage(
            @PathVariable(name = "accountId") UUID accountId,
           HttpServletRequest request);

    @Operation(
            summary = "Alterar imagem de perfil por id da conta.",
            description = "Alteramento da imagem de perfil de um usuário passando o id da conta como path variable. É necessário enviar uma imagem no parametro chamado form-data \"file\"."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Imagem alterada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<ProfileImageAttachmentResponse> updateProfileImage(
            @PathVariable(name = "accountId") UUID accountId,
          @RequestParam(name = "file") MultipartFile file);


    @Operation(
            summary = "Remover imagem de perfil por id da conta.",
            description = "Remoção da imagem de perfil de um usuário passando o id da conta como path variable."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Imagem removida com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<ProfileImageAttachmentResponse> removeProfileImage(
            @PathVariable(name = "accountId") UUID accountId);
}

package br.com.api.heydev.controller;

import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
import br.com.api.heydev.dto.response.user.admin.AdminUserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UserNotFoundException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Validated
public interface IUserController {
    @Operation(
            summary = "Cadastro de conta de usuário.",
            description = "Cadastra uma conta de usuário no sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao cadastrar uma conta", content = @Content),
            }
    )
    ResponseEntity<UserResponse> createAccount(@RequestBody @Valid UserPostRequest request)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    @Operation(
            summary = "[ADMIN] Listagem de contas registradas.",
            description = "Lista todas as contas registradas no sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao realizar a consulta.", content = @Content),
            }
    )
    ResponseEntity<List<AdminUserResponse>> adminlistAllAccounts();


    @Operation(
            summary = "Atualização de username.",
            description = "Atualiza o username de uma conta registrada."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Username atualizado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao atualizar o username.", content = @Content),
            }
    )
    ResponseEntity<UserResponse> updateUsername(
            @PathVariable(value = "userId") UUID userId,
            @RequestParam(name = "username", required = true) @NotBlank String username)
            throws UserNotFoundException, UsernameAlreadyExistsException;

    @Operation(
            summary = "Deleção de uma conta de usuário.",
            description = "Deleta uma conta de usuário registrada no sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Conta deletada, sem conteudo."),
                    @ApiResponse(responseCode = "400", description = "Erro ao deletar a conta.", content = @Content),
            }
    )
    ResponseEntity<String> deleteAccountById(@PathVariable(value = "userId") UUID userId) throws UserNotFoundException;

    @Operation(
            summary = "Detalhamento de uma conta de usupario.",
            description = "Detalha informações de uma conta de usuário."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro ao detalhar o usuário.", content = @Content),
            }
    )
    ResponseEntity<UserResponse> detailsAccountById(@PathVariable(value = "userId") UUID userId) throws UserNotFoundException;

}

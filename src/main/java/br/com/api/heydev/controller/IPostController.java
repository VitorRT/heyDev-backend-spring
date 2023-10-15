package br.com.api.heydev.controller;

import br.com.api.heydev.dto.request.post.PostRequest;
import br.com.api.heydev.dto.request.post.PostUpdateRequest;
import br.com.api.heydev.dto.response.post.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IPostController {

    @Operation(
            summary = "Criar postagem.",
            description = "Criação de postagem de uma conta usuário."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Postagem criada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostRequest request);

    @Operation(
            summary = "Alterar postagem pelo id da postagem..",
            description = "Alteração de postagem de uma conta usuário pelo id da postagem."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Postagem alterada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<PostResponse> updatePost(
            @PathVariable(name = "postId")UUID postId,
            @RequestBody @Valid PostUpdateRequest request);

    @Operation(
            summary = "Detalhar uma postagem pelo id da postagem.",
            description = "Detalhamento dos dados de uma postagem de uma conta usuário pelo id da postagem."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<PostResponse> showPost(@PathVariable(name = "postId")UUID postId);

    @Operation(
            summary = "Deletar uma postagem pelo id da postagem.",
            description = "Deleção dos dados de uma postagem de uma conta usuário pelo id da postagem."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Postagem deletada, sem conteudo."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<Void> deletePost(@PathVariable(name = "postId")UUID postId);

}

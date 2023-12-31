package br.com.api.heydev.controller;

import br.com.api.heydev.dto.request.comment.CommentInCommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentUpdateRequest;
import br.com.api.heydev.dto.response.comment.CommentResponse;
import br.com.api.heydev.dto.response.feed.CommentFeedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ICommentController {

    @Operation(
            summary = "Criar comentário.",
            description = "Criação de comentário de uma postagem."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<CommentResponse> createComment(@RequestBody @Valid CommentPostRequest request);

    @Operation(
            summary = "Alterar comentário pelo id do comentário.",
            description = "Alteração de comentário de uma postagem pelo id do comentário."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Comentário alterado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") UUID commentId, @RequestBody @Valid CommentUpdateRequest request);


    @Operation(
            summary = "Comentar em um comentário de uma postagem pelo id do comentário.",
            description = "Criação de comentário em um comentário de uma postagem pelo id do comentário."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<CommentResponse> commentInComment(@RequestBody @Valid CommentInCommentPostRequest request);

    @Operation(
            summary = "Deletar comentário pelo id do comentário.",
            description = "Deleção de comentário de uma postagem."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Comentário deletado, sem conteúdo."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<Void> deleteComment(@PathVariable("commentId") UUID commentId);

    @Operation(
            summary = "Listar todos os comentários de uma postagem pelo id da postagem.",
            description = "Listagem de todos os dados dos comentários de uma postagem pelo id da postagem."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Comentários retornados com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<List<CommentFeedResponse>> getAllCommentsByPostId(@PathVariable("postId") UUID postId);
}
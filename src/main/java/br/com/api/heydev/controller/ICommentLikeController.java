package br.com.api.heydev.controller;

import br.com.api.heydev.dto.request.commentLike.CommentLikeRequest;
import br.com.api.heydev.dto.response.like.LikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface ICommentLikeController {
    @Operation(
            summary = "Curtir um comentário.",
            description = "Curtir um comentário passando o id da conta e o id do comentário por parâmetro da requisição."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Comentário curtido com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<LikeResponse> like(@RequestBody @Valid CommentLikeRequest request) throws LikeAlreadyExistsException;

    @Operation(
            summary = "Remover uma curtida de um comentário.",
            description = "Remoção de uma curtida de um comentário passando o id da curtida em uma variável da requisição."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Curtida removida sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<Void> removeLike(
            @PathVariable(value = "likeId") UUID likeId
    );
}

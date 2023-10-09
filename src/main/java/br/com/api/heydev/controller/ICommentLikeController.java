package br.com.api.heydev.controller;

import br.com.api.heydev.dto.response.like.LikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    ResponseEntity<LikeResponse> like(
            @RequestParam(name = "account_id", required = true) UUID userAccountId,
            @RequestParam(name = "comment_id", required = true) UUID commentId
            ) throws LikeAlreadyExistsException;

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

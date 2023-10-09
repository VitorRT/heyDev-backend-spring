package br.com.api.heydev.controller;

import br.com.api.heydev.dto.response.postLike.PostLikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface IPostLikeController {

    @Operation(
            summary = "Curtir uma postagem.",
            description = "Curtir uma postagem passando o id da conta e o id da postagem por parâmetro da requisição."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Postagem curtido com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<PostLikeResponse> like(@RequestParam(name = "account_id", required = true) UUID userAccountId,
                                          @RequestParam(name = "post_id", required = true) UUID postId)
            throws LikeAlreadyExistsException;

    @Operation(
            summary = "Remover uma curtida uma postagem.",
            description = "Remoção de uma curtida uma postagem passando o id da curtida em uma variável da requisição."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Curtida removida sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<Void> removeLike(@PathVariable("likeId") UUID likeId);
}

package br.com.api.heydev.controller;

import br.com.api.heydev.dto.response.feed.PostFeedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFeedController {
    @Operation(
            summary = "Feed de postagens",
            description = "Feed de postagens"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Feed retornado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
            }
    )
    ResponseEntity<List<PostFeedResponse>> feed();
}

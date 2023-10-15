package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.ICommentLikeController;
import br.com.api.heydev.dto.request.commentLike.CommentLikeRequest;
import br.com.api.heydev.dto.response.like.LikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import br.com.api.heydev.service.ICommentLikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/comment/like")
@Tag(name = "Comment Like 💬👍")
public class CommentLikeControllerImpl implements ICommentLikeController {
    private ICommentLikeService service;

    @Deprecated
    public CommentLikeControllerImpl(ICommentLikeService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LikeResponse> like(CommentLikeRequest request) throws LikeAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.like(request.userAccountId(), request.commentId()));
    }

    @DeleteMapping(value = "/delete/{likeId}")
    public ResponseEntity<Void> removeLike(UUID likeId) {
        service.removeLike(likeId);
        return ResponseEntity.noContent().build();
    }
}

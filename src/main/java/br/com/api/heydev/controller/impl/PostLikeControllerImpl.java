package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IPostLikeController;
import br.com.api.heydev.dto.response.postLike.PostLikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import br.com.api.heydev.service.IPostLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/post/like")
public class PostLikeControllerImpl implements IPostLikeController {
    private IPostLikeService service;

    public PostLikeControllerImpl(IPostLikeService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostLikeResponse> like(UUID userAccountId, UUID postId) throws LikeAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.like(postId, userAccountId));
    }

    @DeleteMapping(value = "/delete/{likeId}")
    public ResponseEntity<Void> removeLike(UUID likeId) {
        service.removeLike(likeId);
        return ResponseEntity.noContent().build();
    }
}

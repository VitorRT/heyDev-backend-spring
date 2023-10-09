package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IPostController;
import br.com.api.heydev.dto.request.post.PostRequest;
import br.com.api.heydev.dto.request.post.PostUpdateRequest;
import br.com.api.heydev.dto.response.post.PostResponse;
import br.com.api.heydev.service.IPostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@Tag(name = "Post 📝")
public class PostContollerImpl implements IPostController {
    private IPostService service;

    @Deprecated
    public PostContollerImpl(IPostService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> createPost(PostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(request));
    }

    @PutMapping(value = "/update/{postId}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> updatePost(UUID postId, PostUpdateRequest request) {
        return ResponseEntity.ok(service.updatePost(postId, request));
    }

    @GetMapping(value = "/details/{postId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> showPost(UUID postId) {
        return ResponseEntity.ok(service.showPost(postId));
    }

    @DeleteMapping(value = "/delete/{postId}")
    public ResponseEntity<Void> deletePost(UUID postId) {
        service.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}

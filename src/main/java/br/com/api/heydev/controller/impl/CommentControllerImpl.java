package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.ICommentController;
import br.com.api.heydev.dto.request.comment.CommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentUpdateRequest;
import br.com.api.heydev.dto.response.comment.CommentResponse;
import br.com.api.heydev.service.ICommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post/comment")
@Tag(name = "Comment 💬")
public class CommentControllerImpl implements ICommentController {
    private ICommentService service;

    public CommentControllerImpl(ICommentService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> createComment(CommentPostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(request));
    }

    @PatchMapping(value = "/update/{commentId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> updateComment(UUID commentId, CommentUpdateRequest request) {
        return ResponseEntity.ok(service.updateComment(commentId, request));
    }

    @DeleteMapping(value = "delete/{commentId}")
    public ResponseEntity<Void> deleteComment(UUID commentId) {
        service.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}

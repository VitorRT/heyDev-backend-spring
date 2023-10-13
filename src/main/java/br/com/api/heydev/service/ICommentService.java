package br.com.api.heydev.service;

import br.com.api.heydev.dto.request.comment.CommentInCommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentUpdateRequest;
import br.com.api.heydev.dto.response.comment.CommentResponse;

import java.util.UUID;

public interface ICommentService {
    CommentResponse createComment(CommentPostRequest request);
    CommentResponse updateComment(UUID commentId, CommentUpdateRequest request);
    CommentResponse commentInComment(CommentInCommentPostRequest request);
    void deleteComment(UUID commentId);
}

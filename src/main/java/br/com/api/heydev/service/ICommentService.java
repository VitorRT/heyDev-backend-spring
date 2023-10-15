package br.com.api.heydev.service;

import br.com.api.heydev.dto.request.comment.CommentInCommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentUpdateRequest;
import br.com.api.heydev.dto.response.comment.CommentResponse;
import br.com.api.heydev.dto.response.feed.CommentFeedResponse;

import java.util.List;
import java.util.UUID;

public interface ICommentService {
    CommentResponse createComment(CommentPostRequest request);
    CommentResponse updateComment(UUID commentId, CommentUpdateRequest request);
    CommentResponse commentInComment(CommentInCommentPostRequest request);
    List<CommentFeedResponse> getAllCommentsByPostId(UUID postId);
    void deleteComment(UUID commentId);
}

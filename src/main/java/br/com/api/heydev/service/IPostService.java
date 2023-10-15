package br.com.api.heydev.service;

import br.com.api.heydev.dto.request.post.PostRequest;
import br.com.api.heydev.dto.request.post.PostUpdateRequest;
import br.com.api.heydev.dto.response.post.PostResponse;

import java.util.UUID;

public interface IPostService {
    PostResponse createPost(PostRequest request);
    PostResponse updatePost(UUID postId, PostUpdateRequest request);
    PostResponse showPost(UUID postId);
    void deletePost(UUID postId);
}

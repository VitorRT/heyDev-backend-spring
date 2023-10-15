package br.com.api.heydev.service;

import br.com.api.heydev.dto.response.like.LikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;

import java.util.UUID;

public interface IPostLikeService {
    LikeResponse like(UUID postId, UUID userAccountId) throws LikeAlreadyExistsException;
    void removeLike(UUID likePostId);
}

package br.com.api.heydev.service;

import br.com.api.heydev.dto.response.postLike.PostLikeResponse;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;

import java.util.UUID;

public interface IPostLikeService {
    PostLikeResponse like(UUID postId, UUID userAccountId) throws LikeAlreadyExistsException;
    void removeLike(UUID likePostId);
}

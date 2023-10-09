package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.PostEntity;
import br.com.api.heydev.database.entity.PostLikeEntity;
import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.IPostLikeRepository;
import br.com.api.heydev.database.repository.IPostRepository;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.response.postLike.PostLikeResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import br.com.api.heydev.service.IPostLikeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PostLikeServiceImpl implements IPostLikeService {
    private IPostLikeRepository likeRepository;
    private IPostRepository postRepository;
    private IUserRepository userRepository;

    public PostLikeServiceImpl(IPostLikeRepository likeRepository,
                               IPostRepository postRepository,
                               IUserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostLikeResponse like(UUID postId, UUID userAccountId) throws LikeAlreadyExistsException {
        UserEntity user = getUserEntityById(userAccountId);
        PostEntity post = getPostEntityById(postId);

        if(verifyPostLikeExists(postId)) throw new LikeAlreadyExistsException(InternalTypeErrorCodesEnum.E410023);

        PostLikeEntity entity = new PostLikeEntity();
        entity.setUser(user);
        entity.setPost(post);
        entity.setCreated(LocalDateTime.now());

        PostLikeEntity persisted = likeRepository.saveAndFlush(entity);
        log.info("[ DB Persist ] - post successfully liked by: {}", user.getUsername());
        return new PostLikeResponse(persisted.getPostLikeId());
    }

    @Override
    public void removeLike(UUID likePostId) {
        PostLikeEntity like = getPostLikeByPostLikeId(likePostId);
        likeRepository.delete(like);
        log.info("[ DB Delete ] - like successfully removed from post: {}", like.getPost().getPostId());
    }

    private PostLikeEntity getPostLikeByPostLikeId(UUID postLikeId) {
        return likeRepository.findById(postLikeId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410022.getCode())
        );
    }

    private UserEntity getUserEntityById(UUID userAccountId) {
        return userRepository.findById(userAccountId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410000.getCode())
        );
    }

    private PostEntity getPostEntityById(UUID postId){
        return postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410016.getCode())
        );
    }

    private boolean verifyPostLikeExists(UUID postId) {
        Optional<PostLikeEntity> optional = likeRepository.findLikeByPostId(postId);
        if(optional.isPresent()) {
            return true;
        }
        return false;
    }
}

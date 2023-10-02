package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.PostEntity;
import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.IPostRepository;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.request.post.PostRequest;
import br.com.api.heydev.dto.request.post.PostUpdateRequest;
import br.com.api.heydev.dto.response.post.PostResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.service.IPostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {
    private IPostRepository repository;
    private IUserRepository userRepository;

    @Deprecated
    public PostServiceImpl(IPostRepository repository, IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponse createPost(PostRequest request) {
        UserEntity userEntity = getUserEntityById(request.userAccountId());
        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity);
        postEntity.setContent(request.content());

        PostEntity persisted = repository.saveAndFlush(postEntity);

        log.info(" [ DB Persisted ] - Post successfully persisted!");
        return new PostResponse(persisted);
    }

    @Override
    public PostResponse updatePost(UUID postId, PostUpdateRequest request) {
        PostEntity postEntity = getPostEntityById(postId);
        postEntity.setUpdatedAt(LocalDateTime.now());
        postEntity.setContent(request.content());

        PostEntity persisted = repository.saveAndFlush(postEntity);

        log.info("[ DB Persisted ] - Post successfully updated!");
        return new PostResponse(persisted);
    }

    @Override
    public PostResponse showPost(UUID postId) {
        PostEntity postEntity = getPostEntityById(postId);

        log.info("[ DB Query ] - Selection in the post performed successfully!");
        return new PostResponse(postEntity);
    }

    @Override
    public void deletePost(UUID postId) {
        PostEntity postEntity = getPostEntityById(postId);
        repository.delete(postEntity);

        log.info("[ DB Delete ] - Post successfully deleted!");
    }

    private UserEntity getUserEntityById(UUID userAccountId) {
        return userRepository.findById(userAccountId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410000.getCode())
        );
    }

    private PostEntity getPostEntityById(UUID postId){
        return repository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410016.getCode())
        );
    }
}

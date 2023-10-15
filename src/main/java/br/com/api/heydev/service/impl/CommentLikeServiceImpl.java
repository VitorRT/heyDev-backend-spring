package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.CommentEntity;
import br.com.api.heydev.database.entity.CommentLikeEntity;
import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.ICommentLikeRepository;
import br.com.api.heydev.database.repository.ICommentRepository;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.response.like.LikeResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import br.com.api.heydev.service.ICommentLikeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CommentLikeServiceImpl implements ICommentLikeService {
    private ICommentLikeRepository likeRepository;
    private IUserRepository userRepository;
    private ICommentRepository commentRepository;

    @Deprecated
    public CommentLikeServiceImpl(ICommentLikeRepository likeRepository,
                                  IUserRepository userRepository,
                                  ICommentRepository commentRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public LikeResponse like(UUID userAccountId, UUID commentId) throws LikeAlreadyExistsException {
        UserEntity user = getUserEntityById(userAccountId);
        CommentEntity comment = getCommentEntityById(commentId);

        if(verifyCommentLikeExists(commentId)) throw new LikeAlreadyExistsException();

        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setUser(user);
        entity.setComment(comment);
        entity.setCreated(LocalDateTime.now());

        var persisted = likeRepository.saveAndFlush(entity);

        log.info("[ DB Persist ] - comment successfully liked by: {}", user.getUsernameAccount());
        return new LikeResponse(persisted.getCommentLikeId());
    }

    @Override
    public void removeLike(UUID likeId) {
        CommentLikeEntity entity = getCommentLikeEntityById(likeId);
        likeRepository.delete(entity);

        log.info("[ DB Delete ] - comment like successfully deleted in {}", LocalDateTime.now());
    }

    private CommentLikeEntity getCommentLikeEntityById(UUID likeId) {
        return likeRepository.findById(likeId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410024.getCode())
        );
    }

    private UserEntity getUserEntityById(UUID userAccountId) {
        return userRepository.findById(userAccountId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410000.getCode())
        );
    }

    private CommentEntity getCommentEntityById(UUID commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410019.getCode())
        );
    }

    private boolean verifyCommentLikeExists(UUID commentId) {
        Optional<CommentLikeEntity> optional = likeRepository.findByCommentId(commentId);
        if(optional.isPresent()) {
            return true;
        }
        return false;
    }
}

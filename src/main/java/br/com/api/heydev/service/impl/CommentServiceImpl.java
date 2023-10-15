package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.CommentEntity;
import br.com.api.heydev.database.entity.PostEntity;
import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.ICommentRepository;
import br.com.api.heydev.database.repository.IPostRepository;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.request.comment.CommentInCommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentPostRequest;
import br.com.api.heydev.dto.request.comment.CommentUpdateRequest;
import br.com.api.heydev.dto.response.comment.CommentResponse;
import br.com.api.heydev.dto.response.feed.AccountFeedResponse;
import br.com.api.heydev.dto.response.feed.CommentFeedResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.service.ICommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CommentServiceImpl implements ICommentService {
    private ICommentRepository commentRepository;
    private IUserRepository userRepository;
    private IPostRepository postRepository;

    public CommentServiceImpl(ICommentRepository commentRepository, IUserRepository userRepository, IPostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentResponse createComment(CommentPostRequest request) {
        UserEntity user = getUserEntityById(request.userAccountId());
        PostEntity post = getPostEntityById(request.postId());

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUser(user);
        commentEntity.setPost(post);
        commentEntity.setContent(request.content());

        CommentEntity persisted = commentRepository.saveAndFlush(commentEntity);

        log.info("[ DB Persist ] - Comment successfully persisted.");
        return new CommentResponse(persisted);
    }

    @Override
    public CommentResponse updateComment(UUID commentId, CommentUpdateRequest request) {
        CommentEntity commentEntity = getCommentEntityById(commentId);
        commentEntity.setUpdatedAt(LocalDateTime.now());
        commentEntity.setEdited(true);
        commentEntity.setContent(request.content());

        CommentEntity persisted = commentRepository.saveAndFlush(commentEntity);
        log.info("[ DB Persist ] - Comment successfully updated.");
        return new CommentResponse(persisted);
    }

    @Override
    public CommentResponse commentInComment(CommentInCommentPostRequest request) {
        CommentEntity commentEntity = getCommentEntityById(request.parentCommentId());
        UserEntity user = getUserEntityById(request.userAccountId());

        CommentEntity commentInCommentEntity = new CommentEntity();

        commentInCommentEntity.setUser(user);
        commentInCommentEntity.setPost(commentEntity.getPost());
        commentInCommentEntity.setContent(request.content());
        commentInCommentEntity.setParentComment(commentEntity);

        var persited = commentRepository.saveAndFlush(commentInCommentEntity);

        log.info("[ DB Persist ] - create a comment in comment: {}", commentEntity.getCommentId());
        return new CommentResponse(persited);
    }

    @Override
    public List<CommentFeedResponse> getAllCommentsByPostId(UUID postId) {
        getPostEntityById(postId);
        List<CommentFeedResponse> commentResponses = new ArrayList<>();
        Map<UUID, CommentFeedResponse> commentMap = new HashMap<>();

        List<CommentEntity> comments = commentRepository.findAllByPostId(postId);

        for (CommentEntity comment : comments) {
            UUID parentId = null;
            CommentEntity parentComment = comment.getParentComment();
            Long commentsQuantity = 0L;

            if (parentComment != null) {
                parentId = parentComment.getCommentId();
            }

            CommentFeedResponse commentResponse = new CommentFeedResponse(
                    comment.getCommentId(),
                    new AccountFeedResponse(
                            comment.getUser().getUserId(),
                            comment.getUser().getUsername(),
                            comment.getUser().getProfile().getAttachment().getShowUrl()
                    ),
                    comment.getContent(),
                    null,
                    comment.getEdited(),
                    commentRepository.countLikesByCommentId(comment.getCommentId()),
                    commentsQuantity
            );

            if (parentId != null) {
                CommentFeedResponse parentCommentResponse = commentMap.get(parentId);
                if (parentCommentResponse != null) {
                    parentCommentResponse.getChildComments().add(commentResponse);
                    commentsQuantity = Long.valueOf(parentCommentResponse.getChildComments().size());
                    parentCommentResponse.setCommentsQuantity(commentsQuantity);
                }
            } else {
                commentResponses.add(commentResponse);
            }

            commentMap.put(comment.getCommentId(), commentResponse);
        }

        return commentResponses;
    }

    @Override
    public void deleteComment(UUID commentId) {
        CommentEntity commentEntity = getCommentEntityById(commentId);
        commentRepository.delete(commentEntity);

        log.info("[ DB Delete ] - Comment successfully deleted {}.", LocalDateTime.now());
    }

    private CommentEntity getCommentEntityById(UUID commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException(InternalTypeErrorCodesEnum.E410019.getCode())
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
}

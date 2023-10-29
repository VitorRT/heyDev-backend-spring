package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.PostEntity;
import br.com.api.heydev.database.repository.ICommentRepository;
import br.com.api.heydev.database.repository.IFeedPostsRepository;
import br.com.api.heydev.dto.response.feed.AccountFeedResponse;
import br.com.api.heydev.dto.response.feed.PostFeedResponse;
import br.com.api.heydev.service.IFeedService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeedServiceImpl implements IFeedService {
    private IFeedPostsRepository repository;
    private ICommentRepository commentRepository;

    public FeedServiceImpl(IFeedPostsRepository repository, ICommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<PostFeedResponse> feed() {
        List<PostEntity> postEntities = repository.findPostsWithDetails();
        List<PostFeedResponse> postFeedResponses = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostFeedResponse postFeedResponse = new PostFeedResponse(
                    postEntity.getPostId(),
                    new AccountFeedResponse(
                            postEntity.getUser().getUserId(),
                            postEntity.getUser().getUsernameAccount(),
                            postEntity.getUser().getProfile().getAttachment().getShowUrl()
                    ),
                    postEntity.getContent(),
                    postEntity.getCreatedAt(),
                    repository.countLikesByPostId(postEntity.getPostId()),
                    repository.countCommentsByPostId(postEntity.getPostId())
            );

            postFeedResponses.add(postFeedResponse);
        }

        return postFeedResponses;
    }
}

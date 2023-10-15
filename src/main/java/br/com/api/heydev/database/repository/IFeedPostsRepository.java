package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IFeedPostsRepository extends JpaRepository<PostEntity, UUID> {
    @Query(
            "SELECT p " +
                    "FROM PostEntity p " +
                    "JOIN FETCH p.user a " +
                    "LEFT JOIN FETCH p.comments c " +
                    "LEFT JOIN FETCH c.user ca"
    )
    List<PostEntity> findPostsWithDetails();

    @Query("SELECT COUNT(pl) FROM PostLikeEntity pl WHERE pl.post.postId = :postId")
    Long countLikesByPostId(@Param(value = "postId") UUID postId);

    @Query("SELECT COUNT(c) FROM CommentEntity c WHERE c.post.postId = :postId")
    Long countCommentsByPostId(@Param(value = "postId") UUID postId);
}

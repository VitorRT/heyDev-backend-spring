package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ICommentRepository extends JpaRepository<CommentEntity, UUID> {

    @Query("SELECT c FROM CommentEntity c WHERE c.post.postId = :postId")
    List<CommentEntity> findAllByPostId(@Param(value = "postId") UUID postId);


    @Query("SELECT COUNT(cl) FROM CommentLikeEntity cl WHERE cl.comment.commentId = :commentId")
    Long countLikesByCommentId(@Param(value = "commentId") UUID commentId);
}

package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ICommentLikeRepository extends JpaRepository<CommentLikeEntity, UUID> {
    @Query("SELECT l FROM CommentLikeEntity l WHERE l.comment.id = :commentId")
    Optional<CommentLikeEntity> findByCommentId(@Param(value = "commentId") UUID commentId);
}

package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IPostLikeRepository extends JpaRepository<PostLikeEntity, UUID> {

    @Query("SELECT l FROM PostLikeEntity l WHERE l.post.id = :postId")
    Optional<PostLikeEntity> findLikeByPostId(@Param(value = "postId") UUID postId);
}

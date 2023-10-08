package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICommentRepository extends JpaRepository<CommentEntity, UUID> {
}

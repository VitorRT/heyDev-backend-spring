package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPostRepository extends JpaRepository<PostEntity, UUID> {
}

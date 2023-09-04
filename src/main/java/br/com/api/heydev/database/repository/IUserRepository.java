package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsByUsername(@Param(value = "username") String username);

    Boolean existsByEmail(@Param(value = "email") String email);

    Boolean existsByUserId(@Param(value = "userId") UUID userId);


}

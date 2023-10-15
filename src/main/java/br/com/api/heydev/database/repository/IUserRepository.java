package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsByUsernameAccount(@Param(value = "usernameAccount") String usernameAccount);

    Boolean existsByEmail(@Param(value = "email") String email);

    Boolean existsByUserId(@Param(value = "userId") UUID userId);

    Optional<UserEntity> findByEmail(String email);

}

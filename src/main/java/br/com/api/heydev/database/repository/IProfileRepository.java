package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IProfileRepository extends JpaRepository<ProfileEntity, UUID> {
    @Query("SELECT p FROM ProfileEntity p WHERE p.user.id = :accountId")
    Optional<ProfileEntity> findByAccountId(@Param(value = "accountId") UUID accountId);
}

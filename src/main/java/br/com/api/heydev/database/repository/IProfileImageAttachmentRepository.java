package br.com.api.heydev.database.repository;

import br.com.api.heydev.database.entity.ProfileImageAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IProfileImageAttachmentRepository extends JpaRepository<ProfileImageAttachmentEntity, UUID> {

    @Query("SELECT a FROM ProfileImageAttachmentEntity a WHERE a.profile.id = :profileId")
    Optional<ProfileImageAttachmentEntity> findAttachmentByProfileId(@Param(value = "profileId") UUID profileId);
}

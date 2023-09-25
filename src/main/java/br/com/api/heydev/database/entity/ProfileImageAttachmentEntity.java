package br.com.api.heydev.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "profile_image_attachments")
@Data
public class ProfileImageAttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID profileImageAttId;

    @Column(nullable = false)
    @Lob
    private byte[] attachment;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @OneToOne
    @JoinColumn(name = "profile_fk")
    private ProfileEntity profile;
}

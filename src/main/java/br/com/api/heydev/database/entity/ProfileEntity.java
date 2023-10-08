package br.com.api.heydev.database.entity;

import br.com.api.heydev.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "profiles")
@Data
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "profile_id")
    private UUID profileId;

    @Column(name = "social_name", nullable = false, length = 40)
    private String socialName;
    @Column(name = "profile_role", nullable = false, length = 20)
    private ProfileRole profileRole;

    @Column(name = "github_connected", nullable = false)
    private Boolean githubConnected;

    @OneToOne
    @JoinColumn(name = "user_fk")
    private UserEntity user;

}

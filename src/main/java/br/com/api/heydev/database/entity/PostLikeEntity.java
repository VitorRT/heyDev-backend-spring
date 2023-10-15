package br.com.api.heydev.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post_likes")
@Data
@EqualsAndHashCode(of = {"postLikeId"})
public class PostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postLikeId;
    @ManyToOne
    @JoinColumn(name = "user_fk")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_fk")
    private PostEntity post;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
}

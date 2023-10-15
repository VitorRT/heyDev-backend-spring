package br.com.api.heydev.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment_likes")
@Data
@EqualsAndHashCode(of = {"commentLikeId"})
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentLikeId;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "comment_fk")
    private CommentEntity comment;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
}

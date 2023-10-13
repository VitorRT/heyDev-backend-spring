package br.com.api.heydev.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Data
@EqualsAndHashCode(of = {"commentId"})
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_fk")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt = LocalDateTime.now();

    private Boolean edited = false;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CommentLikeEntity> likes;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CommentEntity> childComments;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_comment_fk")
    private CommentEntity parentComment;

    public CommentEntity() { }
}

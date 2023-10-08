package br.com.api.heydev.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private UserEntity user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public PostEntity() {

    }
}

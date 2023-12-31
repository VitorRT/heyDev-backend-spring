package br.com.api.heydev.database.entity;

import br.com.api.heydev.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = {"userId", "username", "email"})
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true)
    private String usernameAccount;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "github_connected", nullable = false)
    private Boolean githubConnected = false;

    @Column(name="github_api_key")
    private String githubAPIKey;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<PostEntity> posts;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<PostLikeEntity> postLikes;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<CommentLikeEntity> commentLikes;

    public UserEntity() {

    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == UserRole.ROLE_ADMIN) {
            return List.of(new SimpleGrantedAuthority(
                    UserRole.ROLE_ADMIN.getRole()),
                    new SimpleGrantedAuthority(UserRole.ROLE_USER.getRole())
            );
        }
        return List.of(new SimpleGrantedAuthority(UserRole.ROLE_USER.getRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package br.com.api.heydev.dto.response.feed;

import br.com.api.heydev.dto.response.feed.AccountFeedResponse;
import br.com.api.heydev.dto.response.feed.CommentFeedResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostFeedResponse {
    private UUID postId;
    private AccountFeedResponse userAccount;
    private String content;
    private LocalDateTime createdAt;
    private Long likesQuantity;
    private Long commentsQuantity;

    public PostFeedResponse(UUID postId, AccountFeedResponse userAccount, String content, LocalDateTime createdAt, Long likesQuantity, Long commentsQuantity) {
        this.postId = postId;
        this.userAccount = userAccount;
        this.content = content;
        this.createdAt = createdAt;
        this.likesQuantity = likesQuantity;
        this.commentsQuantity = commentsQuantity;
    }
}

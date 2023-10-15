package br.com.api.heydev.dto.response.feed;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentFeedResponse {
    private UUID commentId;
    private AccountFeedResponse userAccount;
    private String content;
    private List<CommentFeedResponse> childComments;
    private Boolean isEdited;
    private Long likesQuantity;
    private Long commentsQuantity;

    public CommentFeedResponse(UUID commentId, AccountFeedResponse userAccount, String content, List<CommentFeedResponse> childComments, Boolean isEdited, Long likesQuantity, Long commentsQuantity) {
        this.commentId = commentId;
        this.userAccount = userAccount;
        this.content = content;
        this.childComments = childComments;
        this.isEdited = isEdited;
        this.likesQuantity = likesQuantity;
        this.commentsQuantity = commentsQuantity;
    }

    public List<CommentFeedResponse> getChildComments() {
        if (childComments == null) {
            childComments = new ArrayList<>();
        }
        return childComments;
    }
}

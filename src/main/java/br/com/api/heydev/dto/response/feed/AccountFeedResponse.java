package br.com.api.heydev.dto.response.feed;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccountFeedResponse {
    private UUID accountId;
    private String username;
    private String showImageProfileUrl;

    public AccountFeedResponse(UUID accountId, String username, String showImageProfileUrl) {
        this.accountId = accountId;
        this.username = username;
        this.showImageProfileUrl = showImageProfileUrl;
    }
}

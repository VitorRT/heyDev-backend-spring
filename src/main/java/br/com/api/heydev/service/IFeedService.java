package br.com.api.heydev.service;

import br.com.api.heydev.dto.response.feed.PostFeedResponse;

import java.util.List;

public interface IFeedService {
    List<PostFeedResponse> feed();
}

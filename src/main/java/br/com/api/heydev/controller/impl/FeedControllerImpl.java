package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IFeedController;
import br.com.api.heydev.dto.response.feed.PostFeedResponse;
import br.com.api.heydev.service.IFeedService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedControllerImpl implements IFeedController {
    private IFeedService service;

    public FeedControllerImpl(IFeedService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PostFeedResponse>> feed() {
        return ResponseEntity.ok(service.feed());
    }
}

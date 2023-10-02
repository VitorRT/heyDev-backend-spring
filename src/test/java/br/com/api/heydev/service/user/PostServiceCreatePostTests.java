package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.account.AccountPostRequest;
import br.com.api.heydev.dto.request.post.PostRequest;
import br.com.api.heydev.dto.response.account.AccountResponse;
import br.com.api.heydev.dto.response.post.PostResponse;
import br.com.api.heydev.service.IPostService;
import br.com.api.heydev.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostServiceCreatePostTests {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;
    private AccountPostRequest accountRequest;
    private PostRequest postRequest;
    private AccountResponse accountResponse;
    private PostResponse actual;
    private PostResponse expected;

    @BeforeEach
    void setup() throws Exception {
        // criando uma conta.
        this.accountRequest = new AccountPostRequest(
                "yuuh_takashi",
                "takashi.yui@gmail.com",
                "darklovess2",
                "Yuuh 🎈"
        );
        this.accountResponse = this.userService.createAccount(this.accountRequest);
        // conta criada.

        this.expected = new PostResponse(
                null,
                this.postRequest.content(),
                new AccountResponse(null, this.accountRequest.username()),
                null
        );
    }

    @Test
    void success_createPost_201() {
        this.postRequest = new PostRequest(this.accountResponse.accountId(), "Eu ODEIO os MindBoys!!!!! 😡😡");
        this.actual = this.postService.createPost(this.postRequest);

        Assertions.assertEquals(this.expected.content(), this.actual.content());
        Assertions.assertEquals(this.expected.userAccount().username(), this.actual.userAccount().username());
    }
}

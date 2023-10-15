package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.account.AccountPostRequest;
import br.com.api.heydev.dto.response.account.AccountResponse;
import br.com.api.heydev.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceUpdateUsernameAccountTests {
    @Autowired
    private IUserService userService;
    private AccountPostRequest request;
    private AccountResponse user;
    private AccountResponse expected;

    @BeforeEach
    void setup() throws Exception {
        this.request = new AccountPostRequest("foo_tester","foo@gmail.com","foo123", "foo webtester");
        this.user = this.userService.createAccount(this.request);
        this.expected = new AccountResponse(null, "foo_tester2");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.userService.deleteAccountById(this.user.accountId());
    }

    @Test
    void success_usernameUpdated_200() throws Exception {
        String newUsername = "foo_tester2";
        AccountResponse actual = this.userService.updateUsername(this.user.accountId(), newUsername);

        Assertions.assertEquals(this.expected.username(), actual.username());
    }

    @Test
    void success_usernameSameUpdated_200() throws Exception {
        String sameUsername = "foo_tester";
        AccountResponse actual = this.userService.updateUsername(this.user.accountId(), sameUsername);

        Assertions.assertEquals(this.user.username(), sameUsername);
    }

    @Test
    void failure_usernameExists_400() throws Exception {
        AccountPostRequest userTwo = new AccountPostRequest("mirela", "mirela.cunha@gmail.com", "batata123", "foo webtester");
        AccountResponse userTwoResponse = this.userService.createAccount(userTwo);

        String usernameExists = "mirela";

        Assertions.assertThrows(Exception.class, () ->
            this.userService.updateUsername(this.user.accountId(), usernameExists)
        );
    }
}

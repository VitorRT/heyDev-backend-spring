package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.account.AccountPostRequest;
import br.com.api.heydev.dto.response.account.AccountResponse;
import br.com.api.heydev.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceDetailsUserTests {
    @Autowired
    private IUserService userService;
    private AccountPostRequest request;
    private AccountResponse user;
    private AccountResponse expected;

    @BeforeEach
    void setup() throws Exception {
        this.request = new AccountPostRequest("foo_tester","foo@gmail.com","foo123", "foo webtester");
        this.user = this.userService.createAccount(this.request);
        this.expected = new AccountResponse(null, "foo_tester");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.userService.deleteAccountById(this.user.accountId());
    }

    @Test
    void success_detailsUser_200() throws Exception {
        UUID userId = this.user.accountId();
        AccountResponse actual = this.userService.detailsAccountById(userId);

        Assertions.assertEquals(this.expected.username(), actual.username());
    }

    @Test
    void failure_userIdNotExist_400() throws Exception {
        UUID failureUserId = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> this.userService.detailsAccountById(failureUserId));
    }
}

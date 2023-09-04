package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
import br.com.api.heydev.handler.exception.UserNotFoundException;
import br.com.api.heydev.service.IUserService;
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
    private UserPostRequest request;
    private UserResponse user;
    private UserResponse expected;

    @BeforeEach
    void setup() throws Exception {
        this.request = new UserPostRequest("foo_tester","foo@gmail.com","foo123");
        this.user = this.userService.createAccount(this.request);
        this.expected = new UserResponse(null, "foo_tester");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.userService.deleteAccountById(this.user.userId());
    }

    @Test
    void success_detailsUser_200() throws Exception {
        UUID userId = this.user.userId();
        UserResponse actual = this.userService.detailsAccountById(userId);

        Assertions.assertEquals(this.expected.username(), actual.username());
    }

    @Test
    void failure_userIdNotExist_400() throws Exception {
        UUID failureUserId = UUID.randomUUID();
        Assertions.assertThrows(UserNotFoundException.class, () -> this.userService.detailsAccountById(failureUserId));
    }
}

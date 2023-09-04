package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
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
    private UserPostRequest request;
    private UserResponse user;
    private UserResponse expected;

    @BeforeEach
    void setup() throws Exception {
        this.request = new UserPostRequest("foo_tester","foo@gmail.com","foo123");
        this.user = this.userService.createAccount(this.request);
        this.expected = new UserResponse(null, "foo_tester2");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.userService.deleteAccountById(this.user.userId());
    }

    @Test
    void success_usernameUpdated_200() throws Exception {
        String newUsername = "foo_tester2";
        UserResponse actual = this.userService.updateUsername(this.user.userId(), newUsername);

        Assertions.assertEquals(this.expected.username(), actual.username());
    }

    @Test
    void success_usernameSameUpdated_200() throws Exception {
        String sameUsername = "foo_tester";
        UserResponse actual = this.userService.updateUsername(this.user.userId(), sameUsername);

        Assertions.assertEquals(this.user.username(), sameUsername);
    }

    @Test
    void failure_usernameExists_400() throws Exception {
        UserPostRequest userTwo = new UserPostRequest("mirela", "mirela.cunha@gmail.com", "batata123");
        UserResponse userTwoResponse = this.userService.createAccount(userTwo);

        String usernameExists = "mirela";

        Assertions.assertThrows(Exception.class, () ->
            this.userService.updateUsername(this.user.userId(), usernameExists)
        );
    }
}

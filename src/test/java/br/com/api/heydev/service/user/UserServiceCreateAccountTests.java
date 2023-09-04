package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import br.com.api.heydev.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceCreateAccountTests {
    @Autowired
    private IUserService userService;
    private UserPostRequest request;
    private UserResponse expected;
    private UserResponse actual;

    @BeforeEach
    void setup() {
        this.request = new UserPostRequest("foo_tester", "foo@gmail.com", "footest123");
        this.expected = new UserResponse(null, "foo_tester");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.userService.deleteAccountById(this.actual.userId());
    }

    @Test
    void success_createAccount_200() throws Exception {
        this.actual = this.userService.createAccount(this.request);
        Assertions.assertEquals(this.expected.username(), this.actual.username());
    }

    @Test
    void failure_usernameAlreadyExist_400() throws Exception {
        this.request = new UserPostRequest("foo_tester", "foo@gmail.com", "footest123");
        this.actual = this.userService.createAccount(this.request);
        this.request = new UserPostRequest("foo_tester", "foo2@gmail.com", "footest123");
        Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> this.userService.createAccount(this.request));
    }

    @Test
    void failure_emailAlreadyExist_400() throws Exception {
        this.request = new UserPostRequest("foo_tester", "foo@gmail.com", "footest123");
        this.actual = this.userService.createAccount(this.request);
        this.request = new UserPostRequest("aaaa", "foo@gmail.com", "footest123");
        Assertions.assertThrows(EmailAlreadyExistsException.class, () -> this.userService.createAccount(this.request));
    }
}

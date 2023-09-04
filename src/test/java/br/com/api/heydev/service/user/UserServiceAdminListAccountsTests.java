package br.com.api.heydev.service.user;

import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.admin.AdminUserResponse;
import br.com.api.heydev.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceAdminListAccountsTests {
    @Autowired
    private IUserService userService;
    private UserPostRequest request;
    private List<AdminUserResponse> expected;
    private AdminUserResponse itemExpected;

    @BeforeEach
    void setup() throws Exception {
        this.request = new UserPostRequest("foo_tester", "foo@gmail.com", "test123");
        this.itemExpected = new AdminUserResponse(
                null,
                this.request.username(),
                this.request.email(),
                false,
                null,
                null
        );
        this.expected = new ArrayList<>();
        this.expected.add(this.itemExpected);

        this.userService.createAccount(this.request);
    }

    @Test
    void success_listAllAccounts_200() {
        List<AdminUserResponse> actual = this.userService.adminlistAllAccounts();

        Assertions.assertEquals(1, actual.size());

        actual.forEach(user -> {
            Assertions.assertEquals(this.itemExpected.username(), user.username());
            Assertions.assertEquals(this.itemExpected.email(), user.email());
            Assertions.assertEquals(itemExpected.githubConnected(), user.githubConnected());
        });
    }
}

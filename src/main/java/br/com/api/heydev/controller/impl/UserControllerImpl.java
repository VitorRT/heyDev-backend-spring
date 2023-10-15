package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IUserController;
import br.com.api.heydev.dto.request.account.AccountPostRequest;
import br.com.api.heydev.dto.response.account.AccountResponse;
import br.com.api.heydev.dto.response.account.admin.AdminUserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import br.com.api.heydev.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/useraccount")
@Tag(name = "User Account 👤")
public class UserControllerImpl implements IUserController {
    private IUserService userService;

    @Deprecated
    public UserControllerImpl(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/create"
    )
    public ResponseEntity<AccountResponse> createAccount(@Valid AccountPostRequest request)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAccount(request));
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/list"
    )
    public ResponseEntity<List<AdminUserResponse>> adminlistAllAccounts() {
        return ResponseEntity.ok(userService.adminlistAllAccounts());
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/update/{accountId}"
    )
    public ResponseEntity<AccountResponse> updateUsername(UUID accountId, String username)
            throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(userService.updateUsername(accountId, username));
    }

    @DeleteMapping(
            produces = {MediaType.TEXT_PLAIN_VALUE},
            value = "/delete/{accountId}"
    )
    public ResponseEntity<String> deleteAccountById(UUID accountId) {
        userService.deleteAccountById(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("account deleted successfully!");
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/details/{accountId}"
    )
    public ResponseEntity<AccountResponse> detailsAccountById(UUID accountId) {
        return ResponseEntity.ok(userService.detailsAccountById(accountId));
    }
}

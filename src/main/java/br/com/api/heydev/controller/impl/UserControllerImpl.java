package br.com.api.heydev.controller.impl;

import br.com.api.heydev.controller.IUserController;
import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
import br.com.api.heydev.dto.response.user.admin.AdminUserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UserNotFoundException;
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
@Tag(name = "User Account")
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
    public ResponseEntity<UserResponse> createAccount(@Valid UserPostRequest request)
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
            value = "/update/{userId}"
    )
    public ResponseEntity<UserResponse> updateUsername(UUID userId, String username)
            throws UserNotFoundException, UsernameAlreadyExistsException {
        return ResponseEntity.ok(userService.updateUsername(userId, username));
    }

    @DeleteMapping(
            produces = {MediaType.TEXT_PLAIN_VALUE},
            value = "/delete/{userId}"
    )
    public ResponseEntity<String> deleteAccountById(UUID userId) throws UserNotFoundException {
        userService.deleteAccountById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("account deleted successfully!");
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/details/{userId}"
    )
    public ResponseEntity<UserResponse> detailsAccountById(UUID userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.detailsAccountById(userId));
    }
}

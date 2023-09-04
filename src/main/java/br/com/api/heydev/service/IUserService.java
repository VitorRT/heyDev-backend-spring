package br.com.api.heydev.service;

import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
import br.com.api.heydev.dto.response.user.admin.AdminUserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UserNotFoundException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserResponse createAccount(UserPostRequest request)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    List<AdminUserResponse> adminlistAllAccounts();

    UserResponse updateUsername(UUID userId,String username)
            throws UserNotFoundException, UsernameAlreadyExistsException;

    void deleteAccountById(UUID userId) throws UserNotFoundException;

    UserResponse detailsAccountById(UUID userId) throws UserNotFoundException;
}

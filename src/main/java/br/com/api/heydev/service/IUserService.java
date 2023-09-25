package br.com.api.heydev.service;

import br.com.api.heydev.dto.request.account.AccountPostRequest;
import br.com.api.heydev.dto.response.account.AccountResponse;
import br.com.api.heydev.dto.response.account.admin.AdminUserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    AccountResponse createAccount(AccountPostRequest request)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    List<AdminUserResponse> adminlistAllAccounts();

    AccountResponse updateUsername(UUID userId, String username)
            throws UsernameAlreadyExistsException;

    void deleteAccountById(UUID userId);

    AccountResponse detailsAccountById(UUID userId);
}

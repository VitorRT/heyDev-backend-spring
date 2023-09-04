package br.com.api.heydev.service.impl;

import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.database.repository.IUserRepository;
import br.com.api.heydev.dto.request.user.UserPostRequest;
import br.com.api.heydev.dto.response.user.UserResponse;
import br.com.api.heydev.dto.response.user.admin.AdminUserResponse;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UserNotFoundException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import br.com.api.heydev.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;

    @Deprecated
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createAccount(UserPostRequest request)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        verifyUsernameAlreadyExist(request.username());
        verifyEmailAlreadyExist(request.email());
        UserEntity userEntity = toEntity(request);
        UserEntity userPersisted = this.userRepository.saveAndFlush(userEntity);

        log.info("[ DB Persisted ] - User successfully persisted!");
        return toResponse(userPersisted);
    }

    @Override
    public List<AdminUserResponse> adminlistAllAccounts() {
        List<AdminUserResponse> users =this.userRepository.findAll().stream().map(AdminUserResponse::new).toList();

        log.info("[ DB Query ] - Selection in all user performed successfully!");
        return users;
    }

    @Override
    public UserResponse updateUsername(UUID userId, String username)
            throws UserNotFoundException, UsernameAlreadyExistsException  {
        UserEntity userEntity = getUserById(userId);

        if(userEntity.getUsername().equals(username)) {
            log.info("[ DB Persisted ] - Username successfully edited!");
            return toResponse(userEntity);
        }

        verifyUsernameAlreadyExist(username);
        userEntity.setUsername(username);
        UserEntity userPersisted = this.userRepository.saveAndFlush(userEntity);

        log.info("[ DB Persisted ] - Username successfully edited!");
        return toResponse(userPersisted);
    }

    @Override
    public void deleteAccountById(UUID userId) throws UserNotFoundException {
        UserEntity userEntity = getUserById(userId);
        this.userRepository.delete(userEntity);
    }

    @Override
    public UserResponse detailsAccountById(UUID userId) throws UserNotFoundException {
        UserEntity userEntity = getUserById(userId);

        log.info("[ DB Query ] - Selection in the user performed successfully!");
        return toResponse(userEntity);
    }

    private UserEntity toEntity(UserPostRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.username());
        userEntity.setEmail(request.email());
        userEntity.setPassword(request.password());
        return userEntity;
    }

    private UserResponse toResponse(UserEntity userEntity) {
        return new UserResponse(userEntity);
    }

    private void verifyUsernameAlreadyExist(String username) throws UsernameAlreadyExistsException {
        if(this.userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException();
        }
    }

    private void verifyEmailAlreadyExist(String email) throws EmailAlreadyExistsException {
        if(this.userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

    private UserEntity getUserById(UUID userId) throws UserNotFoundException {
        if(!this.userRepository.existsByUserId(userId)) {
            throw new UserNotFoundException();
        }
        return this.userRepository.findById(userId).get();
    }

}

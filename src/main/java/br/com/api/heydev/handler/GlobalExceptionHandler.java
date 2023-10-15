package br.com.api.heydev.handler;

import br.com.api.heydev.dto.exception.CustomValidationFieldException;
import br.com.api.heydev.dto.handler.CustomErrorResponse;
import br.com.api.heydev.dto.handler.CustomValidationErrorResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.FileNotAnImageException;
import br.com.api.heydev.handler.exception.LikeAlreadyExistsException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<CustomValidationErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        CustomValidationErrorResponse validationErrorResponse = CustomValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isError(true)
                .error("validation error.")
                .code(InternalTypeErrorCodesEnum.E410003.getCode())
                .listValidationErrors(new ArrayList<>())
                .build();

        e.getFieldErrors().forEach(v -> {
            validationErrorResponse.getListValidationErrors().add(
                    CustomValidationFieldException.builder()
                            .field(v.getField())
                            .message(
                                    getInternalTypeErrorCodesEnumByCode(
                                            v.getDefaultMessage()
                                    ).getMessage()
                            )
                            .code(v.getDefaultMessage())
                            .build()
            );
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(validationErrorResponse);
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> usernameAlreadyExistsExceptionHandler(UsernameAlreadyExistsException e) {
        CustomErrorResponse error = buildCustomErrorResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
        CustomErrorResponse error = buildCustomErrorResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    ResponseEntity<CustomErrorResponse> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        CustomErrorResponse error = buildCustomErrorResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({FileNotAnImageException.class})
    public ResponseEntity<CustomErrorResponse> fileNotAnImageExceptionHandler(FileNotAnImageException e) {
        CustomErrorResponse error = buildCustomErrorResponse(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({MultipartException.class})
    public ResponseEntity<CustomErrorResponse> multipartExceptionHandler(MultipartException e) {
        CustomErrorResponse error =  buildCustomErrorResponseByErrorCode(InternalTypeErrorCodesEnum.E410015, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({LikeAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> likeAlreadyExistsExceptionHandler(LikeAlreadyExistsException e) {
        CustomErrorResponse error = buildCustomErrorResponseByErrorCode(
                getInternalTypeErrorCodesEnumByCode(e.getMessage()),
                getInternalTypeErrorCodesEnumByCode(e.getMessage()).getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({JWTDecodeException.class, JWTCreationException.class, JWTVerificationException.class})
    public ResponseEntity<CustomErrorResponse> jwtDecodeException(Exception e) {
        CustomErrorResponse error = buildCustomErrorResponseByErrorCode(
                getInternalTypeErrorCodesEnumByCode("410.025"),
                getInternalTypeErrorCodesEnumByCode("410.025").getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomErrorResponse> noSuchElementException(NoSuchElementException e) {
        CustomErrorResponse error = buildCustomErrorResponseByErrorCode(
                getInternalTypeErrorCodesEnumByCode("410.026"),
                getInternalTypeErrorCodesEnumByCode("410.026").getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private InternalTypeErrorCodesEnum getInternalTypeErrorCodesEnumByCode(String errorCode) {
        for(InternalTypeErrorCodesEnum error : InternalTypeErrorCodesEnum.values()) {
            if(error.getCode().equals(errorCode)) {
                return error;
            }
        }
        return InternalTypeErrorCodesEnum.E500000;
    }

    private CustomErrorResponse buildCustomErrorResponse(Exception e) {
        return CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isError(true)
                .error(getInternalTypeErrorCodesEnumByCode(e.getMessage()).getMessage())
                .code(e.getMessage())
                .build();
    }

    private CustomErrorResponse buildCustomErrorResponseByErrorCode(InternalTypeErrorCodesEnum errorCode, String message) {
        return CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isError(true)
                .error(message)
                .code(errorCode.getCode())
                .build();
    }
}

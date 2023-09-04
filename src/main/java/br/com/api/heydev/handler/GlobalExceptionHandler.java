package br.com.api.heydev.handler;

import br.com.api.heydev.dto.exception.CustomValidationFieldException;
import br.com.api.heydev.dto.handler.CustomErrorResponse;
import br.com.api.heydev.dto.handler.CustomValidationErrorResponse;
import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import br.com.api.heydev.handler.exception.EmailAlreadyExistsException;
import br.com.api.heydev.handler.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

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
        CustomErrorResponse error = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isError(true)
                .error(InternalTypeErrorCodesEnum.E410001.getMessage())
                .code(InternalTypeErrorCodesEnum.E410001.getCode())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
        CustomErrorResponse error = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isError(true)
                .error(InternalTypeErrorCodesEnum.E410002.getMessage())
                .code(InternalTypeErrorCodesEnum.E410002.getCode())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }


    private InternalTypeErrorCodesEnum getInternalTypeErrorCodesEnumByCode(String errorCode) {
        for(InternalTypeErrorCodesEnum error : InternalTypeErrorCodesEnum.values()) {
            if(error.getCode().equals(errorCode)) {
                return error;
            }
        }
        return InternalTypeErrorCodesEnum.E500000;
    }
}

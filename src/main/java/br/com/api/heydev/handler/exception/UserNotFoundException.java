package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;

public class UserNotFoundException extends ErrorCodeException {
    private InternalTypeErrorCodesEnum errorCode;

    public UserNotFoundException() {
        super(InternalTypeErrorCodesEnum.E100000);
    }

    public UserNotFoundException(String message) {
        super(InternalTypeErrorCodesEnum.E100000, message);
    }

    public InternalTypeErrorCodesEnum getErrorCode() {
        return this.errorCode;
    }
}
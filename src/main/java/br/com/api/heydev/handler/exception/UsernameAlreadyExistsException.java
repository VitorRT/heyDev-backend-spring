package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;

public class UsernameAlreadyExistsException extends ErrorCodeException {
    private InternalTypeErrorCodesEnum errorCode;

    public UsernameAlreadyExistsException() {
        super(InternalTypeErrorCodesEnum.E410001);
    }

    public UsernameAlreadyExistsException(String message) {
        super(InternalTypeErrorCodesEnum.E410001, message);
    }

    public InternalTypeErrorCodesEnum getErrorCode() {
        return this.errorCode;
    }
}

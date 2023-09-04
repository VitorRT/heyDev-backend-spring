package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;

public class EmailAlreadyExistsException extends ErrorCodeException {
    public EmailAlreadyExistsException() {
        super(InternalTypeErrorCodesEnum.E410002);
    }

    public EmailAlreadyExistsException(String message) {
        super(InternalTypeErrorCodesEnum.E410002, message);
    }
}

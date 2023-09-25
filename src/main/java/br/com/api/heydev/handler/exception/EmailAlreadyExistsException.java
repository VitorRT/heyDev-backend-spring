package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;

public class EmailAlreadyExistsException extends ErrorCodeException {
    private String message;
    public EmailAlreadyExistsException() {
        super(InternalTypeErrorCodesEnum.E410002);
        this.message = InternalTypeErrorCodesEnum.E410002.getCode();
    }

    public EmailAlreadyExistsException(String message) {
        super(InternalTypeErrorCodesEnum.E410002, message);
        this.message = InternalTypeErrorCodesEnum.E410002.getCode();
    }
}

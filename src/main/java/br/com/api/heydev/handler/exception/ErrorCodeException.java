package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;

public class ErrorCodeException extends Exception {
    private InternalTypeErrorCodesEnum errorCode;

    protected ErrorCodeException(InternalTypeErrorCodesEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected ErrorCodeException(InternalTypeErrorCodesEnum errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
    }

    public InternalTypeErrorCodesEnum getCode() {
        return this.errorCode;
    }
}

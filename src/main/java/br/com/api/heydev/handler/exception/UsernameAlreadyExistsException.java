package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import lombok.Getter;

@Getter
public class UsernameAlreadyExistsException extends ErrorCodeException {
    private String message;

    public UsernameAlreadyExistsException() {
        super(InternalTypeErrorCodesEnum.E410001);
        this.message = InternalTypeErrorCodesEnum.E410001.getCode();
    }

    public UsernameAlreadyExistsException(String message) {
        super(InternalTypeErrorCodesEnum.E410001, message);
        this.message = InternalTypeErrorCodesEnum.E410001.getCode();
    }

}

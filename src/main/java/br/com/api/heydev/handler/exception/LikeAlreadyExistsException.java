package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import lombok.Getter;

@Getter
public class LikeAlreadyExistsException extends ErrorCodeException{
    private String message;

    public LikeAlreadyExistsException() {
        super(InternalTypeErrorCodesEnum.E410023);
        this.message = InternalTypeErrorCodesEnum.E410023.getCode();
    }
    public LikeAlreadyExistsException(InternalTypeErrorCodesEnum errorCode) {
        super(errorCode);
        this.message = errorCode.getCode();
    }

    public LikeAlreadyExistsException(InternalTypeErrorCodesEnum errorCode, String message) {
        super(InternalTypeErrorCodesEnum.E410023, message);
        this.message = InternalTypeErrorCodesEnum.E410023.getCode();
    }
}

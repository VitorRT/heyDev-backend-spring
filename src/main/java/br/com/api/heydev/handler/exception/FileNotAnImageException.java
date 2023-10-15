package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import lombok.Getter;

@Getter
public class FileNotAnImageException extends RuntimeException {
    private String message;

    public FileNotAnImageException() {
        this.message = InternalTypeErrorCodesEnum.E410012.getCode();
    }
}

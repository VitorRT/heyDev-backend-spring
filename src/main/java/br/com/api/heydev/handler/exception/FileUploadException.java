package br.com.api.heydev.handler.exception;

import br.com.api.heydev.enums.InternalTypeErrorCodesEnum;
import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException{
    private String errorCode;

    public FileUploadException() {
        this.errorCode = InternalTypeErrorCodesEnum.E410013.getCode();
    }
}

package br.com.api.heydev.dto.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomValidationFieldException {
    private String field;
    private String message;
    private String code;
}

package br.com.api.heydev.dto.handler;

import br.com.api.heydev.dto.exception.CustomValidationFieldException;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomValidationErrorResponse {
    private Integer status;
    private Boolean isError;
    private String error;
    private String code;
    private List<CustomValidationFieldException> listValidationErrors;
}

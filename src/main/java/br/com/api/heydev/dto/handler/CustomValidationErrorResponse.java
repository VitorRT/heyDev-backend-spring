package br.com.api.heydev.dto.handler;

import br.com.api.heydev.dto.exception.CustomValidationFieldException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomValidationErrorResponse {
    private Integer status;
    private Boolean isError;
    private String error;
    private String code;
    private List<CustomValidationFieldException> listValidationErrors;
}

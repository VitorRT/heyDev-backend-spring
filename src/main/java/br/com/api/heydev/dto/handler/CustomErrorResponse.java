package br.com.api.heydev.dto.handler;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private Integer status;
    private Boolean isError;
    private String error;
    private String code;
}

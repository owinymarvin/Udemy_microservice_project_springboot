package com.dmarv.microservice.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDto {
    // for client to understand if it is success or not
    private String statusCode;
    private String statusMessage;

}
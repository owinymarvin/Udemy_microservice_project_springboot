package com.dmarv.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response Information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(description = "API path invoked by client")
    private String apiPath;


    @Schema(
            description = "HTTP Status Code returned by the API",
            example = "500"
    )
    private HttpStatus errorCode;


    @Schema(
            description = "Error message representing the error " +
                    "that happened"
    )
    private String errorMessage;


    @Schema(
            description = "Time at which the error occurred",
            example = "2026-01-01T00:00:00"
    )
    private LocalDateTime errorTime;
}

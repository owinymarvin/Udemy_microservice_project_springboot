package com.dmarv.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful responses " +
                "information"
)
@Data @AllArgsConstructor
public class ResponseDto {
    // for client to understand if it is success or not
    @Schema(

            description = "Status code in the response",
            example = "200"
    )
    private String statusCode;


    @Schema(
            description = "Status message in the response",
            example = "Request processed successfully"
    )
    private String statusMessage;

}

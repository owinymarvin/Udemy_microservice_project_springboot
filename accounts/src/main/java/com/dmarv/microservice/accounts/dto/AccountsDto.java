package com.dmarv.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @Schema(
            description = "Account number for Eazy Bank Account",
            example = "3456789122"
    )
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be 10 digits")
    private Long accountNumber;


    @Schema(
            description = "Account type for Eazy Bank Account",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;


    @Schema(
            description = "Branch address for Eazy Bank Account",
            example = "123, Olive Street, Olive"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}

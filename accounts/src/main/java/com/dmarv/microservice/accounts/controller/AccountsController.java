package com.dmarv.microservice.accounts.controller;

import com.dmarv.microservice.accounts.constants.AccountsConstants;
import com.dmarv.microservice.accounts.dto.CustomerDto;
import com.dmarv.microservice.accounts.dto.ErrorResponseDto;
import com.dmarv.microservice.accounts.dto.ResponseDto;
import com.dmarv.microservice.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Eazy Bank",
        description = "CREATE, " +
                "UPDATE, FETCH and DELETE"
)
public class AccountsController {
    private IAccountsService iAccountsService;

    @PostMapping("/create")
    @Operation(
            summary = "CREATE Account REST API",
            description = "Creates a new Customer as well as an " +
                    "Account for the new user Automatically"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED, Account Created " +
                    "Successfully"
    )
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    @Operation(
            summary = "FETCH Account REST API",
            description = "Fetch a Customer and Account details of " +
                    "a customer based on their mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status Ok"
    )
    public ResponseEntity<CustomerDto> fetchAccountDetailsIdentifiedByMobileNumber(
            @RequestParam @Pattern(regexp = "^[0-9]{10}$",
                    message = "Account number must be 10 digits") String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    @Operation(
            summary = "UPDATE Account REST API",
            description = "Update customers Details and their " +
                    "Account details based on their account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })

    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated =
                iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseDto(AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_UPDATE)
            );
        }
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "DELETE Account REST API",
            description = "Delete customers Details and their " +
                    "Account details based on their mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR"
            )
    })
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam @Pattern(regexp = "^[0-9]{10}$",
                    message = "Account number must be 10 digits") String mobileNumber) {
        boolean isDeleted =
                iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200)
            );
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseDto(AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_DELETE)
            );
        }
    }

}

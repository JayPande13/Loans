package com.example.Loans.Controller;

import com.example.Loans.Contants.LoansConstants;
import com.example.Loans.Dto.ErrorResponseDto;
import com.example.Loans.Dto.LoansDto;
import com.example.Loans.Dto.ResponseDto;
import com.example.Loans.Services.ILoanService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/loans/")
@AllArgsConstructor
@Validated
@Tag(name = "Loan Creating Controller",
        description = "This Controller gives endpoints for Creating, Updating, Fetching, Deleting a Loan"
)
public class LoanController {

    private ILoanService loanService;

    @Operation(
            description = "API for creating New Loan",
            summary = "on Hitting this API with mobile number you will be able to Create new Loan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Loan Created Successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Loan not created"
            )

    })
    @PostMapping("create")
    public ResponseEntity<ResponseDto> createLoan(@Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number must be 10 digits") @RequestParam String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @Operation(
            description = "API for updating Loan",
            summary = "on Hitting this API with Loan data, can update its database entry"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Loan Created Successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Loan not created",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Loan creation failed"
            )

    })
    @PostMapping("update")
    public ResponseEntity<ResponseDto> updateLoad(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = loanService.updateLoan(loansDto);
        if (isUpdated) {
            return new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE), HttpStatus.EXPECTATION_FAILED);

        }
    }

    @Operation(
            description = "API for fetching Loan",
            summary = "on Hitting this API with mobile number and you will get Loan Details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Loan Fetched Successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Loan not fetched",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )

    })
    @GetMapping("fetch")
    public ResponseEntity<LoansDto> fetchLoan(@Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number must be 10 digits") @RequestParam String mobileNumber) {
        LoansDto loansDto= loanService.getLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            description = "API for deleting Loan",
            summary = "on Hitting this API with mobile number you can delete a Loan Data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Loan Created Successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Loan not created",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Loan creation failed"
            )

    })
    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> deleteLoan(@Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number must be 10 digits") @RequestParam String mobileNumber) {
        boolean isDeleted =  loanService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE), HttpStatus.EXPECTATION_FAILED);

        }
    }


}

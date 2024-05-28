package com.example.Loans.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loan",
        description = "Return Created Loan Information")
public class LoansDto {

    @Schema(description = "Mobile Number Attached with Loan", example = "5943513155")
    @Pattern(regexp = "^$|[0-9]{10}",message = "Mobile Number must be 10 digits")
    public String mobileNumber;

    @Schema(description = "Load Number Attached with Loan", example = "3645755")
    @NotEmpty(message = "Load Number Cannot be Empty")
    public String loanNumber;

    @Schema(description = "Type of Loan", example = "Education, home etc.")
    @NotEmpty(message = "Load Type Cannot be Empty")
    public String loanType;

    @Schema(description = "Total Loan Amount", example = "700000")
    @Positive(message = "Loan Amount should be greater than 0")
    @NotEmpty(message = "Total Loan Amount Cannot be Empty")
    public Integer totalLoan;

    @Schema(description = "Amount Paid of Loan", example = "522444")
    @PositiveOrZero(message = "Loan Paid should be Zero or greater than Zero")
    @NotEmpty(message = "Amount Paid Cannot be Empty")
    public Integer amountPaid;

    @Schema(description = "Outstanding amount of loan", example = "225412")
    @PositiveOrZero(message = "Outstanding Amount should be Zero or greater than Zero")
    @NotEmpty(message = "Outstanding Amount Cannot be Empty")
    public Integer outstandingAmount;
}

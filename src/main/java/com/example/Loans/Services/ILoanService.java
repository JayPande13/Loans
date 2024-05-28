package com.example.Loans.Services;

import com.example.Loans.Dto.LoansDto;

public interface ILoanService {

    /**
     * create loan
     *
     * @param mobileNumber mobileNumber
     */
    public void createLoan(String mobileNumber);

    /**
     * update loan
     *
     * @param loansDto loansDto
     * @return {@link boolean}
     */
    public boolean updateLoan(LoansDto loansDto);

    /**
     * get loan
     *
     * @param mobileNumber mobileNumber
     * @return {@link LoansDto}
     * @see LoansDto
     */
    public LoansDto getLoan(String mobileNumber);


    /**
     * delete loan
     *
     * @param mobileNumber mobileNumber
     * @return {@link boolean}
     */
    public boolean deleteLoan(String mobileNumber);
}

package com.example.Loans.Services.Impl;

import com.example.Loans.Contants.LoansConstants;
import com.example.Loans.Dto.LoansDto;
import com.example.Loans.Entities.Loans;
import com.example.Loans.Exception.LoanAlreadyExistsException;
import com.example.Loans.Exception.ResourceNotFoundException;
import com.example.Loans.Mapper.LoansMapper;
import com.example.Loans.Repository.LoansRepository;
import com.example.Loans.Services.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoansRepository loansRepository;

    /**
     * create loan
     *
     * @param mobileNumber mobileNumber
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists with this mobile number : " + mobileNumber);
        }
        loansRepository.save(createNewLoad(mobileNumber));

    }

    private Loans createNewLoad(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
    /**
     * update loan
     *
     * @param loansDto loansDto
     * @return {@link boolean}
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
      Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(() -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     * get loan
     *
     * @param mobileNumber mobileNumber
     * @return {@link LoansDto}
     * @see LoansDto
     */
    @Override
    public LoansDto getLoan(String mobileNumber) {
      Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
      LoansDto loansDto = LoansMapper.mapToLoansDto(loans.get(), new LoansDto());
      return loansDto;
    }

    /**
     * delete loan
     *
     * @param mobileNumber mobileNumber
     * @return {@link boolean}
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;

    }


}

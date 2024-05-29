package com.example.Loans.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Loans extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long LoanId;

    public String mobileNumber;

    public String loanNumber;

    public String loanType;

    public Integer totalLoan;

    public Integer amountPaid;

    public Integer outstandingAmount;
}

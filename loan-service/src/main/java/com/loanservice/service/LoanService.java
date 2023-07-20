package com.loanservice.service;

import com.loanservice.entity.LoanEntity;

import java.util.List;

public interface LoanService {
    List<LoanEntity> getAllLoans();

    LoanEntity getLoanBYId(Long id);
    LoanEntity updateLoan(Long id, LoanEntity loan);

    String deleteLoan(Long id);

    List<LoanEntity> getLoanByStatus(String status);

    LoanEntity getLoanByIdAndStatus(String status, Long id);

    List<LoanEntity> getOverDueLoans();

    LoanEntity save(LoanEntity loan);

}

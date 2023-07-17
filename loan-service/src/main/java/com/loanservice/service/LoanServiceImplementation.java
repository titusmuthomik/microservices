package com.loanservice.service;

import com.loanservice.entity.LoanEntity;
import com.loanservice.repository.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplementation implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public LoanEntity getLoanBYId(Long id) {
        return loanRepository.findById(id).get();
    }

    @Override
    public LoanEntity updateLoan(Long id, LoanEntity loan) {
        LoanEntity existingLoan = loanRepository.findById(id).get();

        existingLoan.setAmount(loan.getAmount());
        existingLoan.setStatus(loan.getStatus());
        existingLoan.setDueDate(loan.getDueDate());

        loanRepository.save(existingLoan);

        return existingLoan;

    }

    @Override
    public String deleteLoan(Long id) {
        loanRepository.deleteById(id);

        return "Loan successfully deleted";
    }

    @Override
    public List<LoanEntity> getLoanByStatus(String status) {
        List<LoanEntity> loanEntityList = loanRepository.findAll();

        return loanEntityList.stream()
                .filter(loanEntity -> loanEntity.getStatus().equalsIgnoreCase(status))
                .map(loanFilter -> {
                    LoanEntity loan = new LoanEntity();
                    BeanUtils.copyProperties(loanFilter, loan);
                    return loan;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LoanEntity getLoanByIdAndStatus(String status, Long id) {
        List<LoanEntity> loanEntityList = loanRepository.findAll();


        return loanEntityList.stream()
                .filter(loan -> loan.getStatus().equalsIgnoreCase(status))
                .filter(loanEntity -> loanEntity.getLoanId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No loan of status " + status + " with the id of NOT_FOUND"));
    }

    @Override
    public List<LoanEntity> getOverDueLoans() {
        return null;
    }

    @Override
    public LoanEntity save(LoanEntity loan) {
        return loanRepository.save(loan);
    }
}

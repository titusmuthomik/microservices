package com.loanservice.controller;

import com.loanservice.entity.LoanEntity;
import com.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanEntity> save(@RequestBody LoanEntity loan){
        return new ResponseEntity<>(loanService.save(loan), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LoanEntity>> getAllLoans(){
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public LoanEntity getLoanById(@PathVariable Long id) {

        return new ResponseEntity<>(loanService.getLoanBYId(id), HttpStatus.OK).getBody();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanEntity>> getLoansByStatus(@PathVariable String status) {

        return  new ResponseEntity<>(loanService.getLoanByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/status/{status}/{id}")
    public ResponseEntity<LoanEntity> getBYIdAndStatus(@PathVariable String status, @PathVariable Long id) {
        return new ResponseEntity<>(loanService.getLoanByIdAndStatus(status, id), HttpStatus.OK);
    }
}

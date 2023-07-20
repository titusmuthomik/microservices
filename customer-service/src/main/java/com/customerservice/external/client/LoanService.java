package com.customerservice.external.client;

import com.customerservice.external.model.LoanEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LOAN-SERVICE", url = "http://localhost:8081/api/v1/loans")
public interface LoanService {

     @GetMapping("/{id}")
     ResponseEntity<LoanEntity> getLoanById(@PathVariable Long id);
}

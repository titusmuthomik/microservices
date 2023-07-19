package com.customerservice.external.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanEntity {

    private Long loanId;
    private String status;
    private Long  customerId;
    private Double amount;
    private String disbursementDate;
    private String paymentDate;
    private String dueDate;
}
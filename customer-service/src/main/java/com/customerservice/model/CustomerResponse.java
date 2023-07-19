package com.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private long nationalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LoanDetails loanDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoanDetails {
        private Long loanId;
        private String status;
        private Long  customerId;
        private Double amount;
        private String disbursementDate;
        private String paymentDate;
        private String dueDate;
    }
}

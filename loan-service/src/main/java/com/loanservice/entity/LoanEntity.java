package com.loanservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;

    private String status;
    private Long  customerId;
    private Double amount;
    private String disbursementDate;
    private String paymentDate;
    private String dueDate;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private CustomerEntity customer;
}
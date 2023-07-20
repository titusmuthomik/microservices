package com.customerservice.service;

import com.customerservice.entity.CustomerEntity;
import com.customerservice.external.client.LoanService;
import com.customerservice.external.model.LoanEntity;
import com.customerservice.model.CustomerResponse;
import com.customerservice.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Log4j2
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CustomerResponse save(CustomerEntity customer) {

//        TODO create loan before assignment


//        assign customer a loan of id 1
        log.info("Calling loan service to assign customer a loan");

        Long loanId = 1L;

        String url = "http://LOAN-SERVICE/api/v1/loans/" + loanId;
        LoanEntity loan = restTemplate.getForObject(url, LoanEntity.class);

        assert loan != null;
        CustomerResponse.LoanDetails loandetails = CustomerResponse.LoanDetails.builder()
                .loanId(loan.getLoanId())
                .amount(loan.getAmount())
                .customerId(loan.getCustomerId())
                .status(loan.getStatus())
                .paymentDate(loan.getPaymentDate())
                .disbursementDate(loan.getDisbursementDate())
                .dueDate(loan.getDueDate())
                .build();

        return CustomerResponse.builder()
                .email(customer.getEmail())
                .customerId(customer.getCustomerId())
                .nationalId(customer.getNationalId())
                .phone(customer.getPhone())
                .loanDetails(loandetails)
                .build();
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {

        LoanEntity loan = loanService.getLoanById(1L).getBody();

        Optional<CustomerEntity> customer = customerRepository.findById(id);


        CustomerResponse.LoanDetails loanDetails = CustomerResponse.LoanDetails.builder()
                .loanId(loan.getLoanId())
                .amount(loan.getAmount())
                .status(loan.getStatus())
                .dueDate(loan.getDueDate())
                .customerId(loan.getCustomerId())
                .disbursementDate(loan.getDisbursementDate())
                .paymentDate(loan.getPaymentDate())
                .build();


        return CustomerResponse.builder()
                .customerId(customer.get().getCustomerId())
                .phone(customer.get().getPhone())
                .firstName(customer.get().getFirstName())
                .lastName(customer.get().getLastName())
                .email(customer.get().getEmail())
                .loanDetails(loanDetails)
                .build();

    }

    @Override
    public CustomerEntity updateCustomer(Long id, CustomerEntity customer) {
        CustomerEntity existingCustomer = customerRepository.findById(id).get();

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setNationalId(customer.getNationalId());

        customerRepository.save(existingCustomer);
        return existingCustomer;
    }

    @Override
    public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "Customer successfully deleted";
    }
}

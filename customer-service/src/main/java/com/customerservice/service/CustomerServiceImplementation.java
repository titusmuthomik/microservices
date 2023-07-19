package com.customerservice.service;

import com.customerservice.entity.CustomerEntity;
import com.customerservice.external.client.LoanService;
import com.customerservice.external.model.LoanEntity;
import com.customerservice.model.CustomerResponse;
import com.customerservice.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanService loanService;

    @Override
    public CustomerEntity save(CustomerEntity customer) {
        customerRepository.save(customer);
        return customer;
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

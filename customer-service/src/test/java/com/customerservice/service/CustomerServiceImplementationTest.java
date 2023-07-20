package com.customerservice.service;

import com.customerservice.entity.CustomerEntity;
import com.customerservice.external.client.LoanService;
import com.customerservice.external.model.LoanEntity;
import com.customerservice.model.CustomerResponse;
import com.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceImplementationTest {



    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LoanService loanService;
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    CustomerService customerService = new CustomerServiceImplementation();


    @DisplayName("getCustomerById")
    @Test
    void getCustomerById_success() {
//        calls loan service to assign a loan of id 1
        LoanEntity loan = getMockLoan();
        when(loanService.getLoanById(anyLong())).thenReturn(new ResponseEntity<>(loan, HttpStatus.OK));

        CustomerResponse customer = getMockCustomer();
        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customer, entity);
        Optional<CustomerResponse> customerOptional = Optional.of(customer);
        Optional<CustomerEntity> customerOptionalEntity = Optional.of(entity);
        when(customerRepository.findById(anyLong()))
                .thenReturn(customerOptionalEntity);

        // Call the method under test
        Long customerId = 1L;
        CustomerResponse result = customerService.getCustomerById(customerId);

        // Verify that the methods were called with the correct parameters
        verify(loanService, times(1)).getLoanById(customerId);
        verify(customerRepository, times(1)).findById(customerId);

        assertEquals(customer, result);
    }

    private LoanEntity getMockLoan() {
        return LoanEntity.builder()
                .loanId(1L)
                .amount(5000.0)
                .customerId(1L)
                .disbursementDate("2023-03-05")
                .dueDate("2023-05-05")
                .paymentDate("2023-05-05")
                .status("disbursed")
                .build();


    }

private CustomerResponse getMockCustomer() {

        LoanEntity loan = getMockLoan();

        CustomerResponse.LoanDetails loanDetails = new CustomerResponse.LoanDetails();

        BeanUtils.copyProperties(loan, loanDetails);


    CustomerResponse entity =  CustomerResponse.builder()
            .customerId(1L)
            .email("titus@mail.com")
            .firstName("Titus")
            .lastName("Kalunge")
            .nationalId(0)
            .phone("0713398918")
            .loanDetails(loanDetails)
            .build();

    CustomerResponse res = new CustomerResponse();
    BeanUtils.copyProperties(entity, res);

    return res;


}

}
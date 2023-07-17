package com.customerservice.service;

import com.customerservice.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    CustomerEntity save(CustomerEntity customer);

    List<CustomerEntity> getAllCustomers();

    CustomerEntity getCustomerById(Long id);

    CustomerEntity updateCustomer(Long id, CustomerEntity customer);

    String deleteCustomer(Long id);


}

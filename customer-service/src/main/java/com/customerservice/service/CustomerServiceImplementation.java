package com.customerservice.service;

import com.customerservice.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{
    @Override
    public CustomerEntity save(CustomerEntity customer) {
        return null;
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return null;
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        return null;
    }

    @Override
    public CustomerEntity updateCustomer(Long id, CustomerEntity customer) {
        return null;
    }

    @Override
    public String deleteCustomer(Long id) {
        return null;
    }
}

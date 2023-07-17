package com.customerservice.service;

import com.customerservice.entity.CustomerEntity;
import com.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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
    public CustomerEntity getCustomerById(Long id) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        return customer.get();
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

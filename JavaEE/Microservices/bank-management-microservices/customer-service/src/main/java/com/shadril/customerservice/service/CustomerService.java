package com.shadril.customerservice.service;

import com.shadril.customerservice.model.Customer;
import com.shadril.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(()->new NullPointerException("Customer not available."));
    }
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }
}

package com.shadril.customerservice.controller;

import com.shadril.customerservice.CustomerServiceApplication;
import com.shadril.customerservice.client.AccountClient;
import com.shadril.customerservice.model.Customer;
import com.shadril.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountClient accountClient;

    @PostMapping("/add")
    public ResponseEntity<Customer> add(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll(){
        return new ResponseEntity<>(customerService.getCustomer(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
    @GetMapping("/with-accounts")
    public List<Customer> findAllWithAccount(){
        List<Customer> customers= customerService.getCustomer();

        customers.forEach(customer ->
                        customer.setAccounts(
                                accountClient.findByCustomer(customer.getId())
                        ));
        return customers;
    }

}

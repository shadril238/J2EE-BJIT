package com.shadril.customerservice.client;

import com.shadril.customerservice.model.Account;
import com.shadril.customerservice.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface AccountClient {
    @GetExchange("account/customer/{customerId}")
    public List<Account> findByCustomer(@PathVariable Long customerId);

}

package com.shadril.accountservice.controller;

import com.shadril.accountservice.model.Account;
import com.shadril.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public Account add(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/all")
    public List<Account> findAll() {
        return accountService.getAccount();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return accountService.getByAccountId(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> findByCustomer(@PathVariable Long customerId) {
        return accountService.findByCustomer(customerId);
    }

}

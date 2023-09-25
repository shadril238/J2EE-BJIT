package com.shadril.accountservice.service;

import com.shadril.accountservice.model.Account;
import com.shadril.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getByAccountId(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new NullPointerException("Id not available"));
    }

    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    public List<Account> findByCustomer(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId).orElseThrow(()->new NullPointerException("Id not available"));
    }
}

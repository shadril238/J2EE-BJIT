package com.shadril.springbootjdbc.controller;

import com.shadril.springbootjdbc.model.Expense;
import com.shadril.springbootjdbc.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private IExpenseRepository expenseRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenseList = expenseRepository.getAll();
        return new ResponseEntity<>(expenseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Integer id){
        Optional<Expense> expense = expenseRepository.getById(id);
        return new ResponseEntity<>(expense.get(), HttpStatus.OK);
    }
}

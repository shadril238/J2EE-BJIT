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
public class ExpenseController {
    @Autowired
    private IExpenseRepository expenseRepository;

    @GetMapping("/expense/all")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenseList = expenseRepository.getAll();
        return new ResponseEntity<>(expenseList, HttpStatus.OK);
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Integer id){
        Optional<Expense> expense = expenseRepository.getById(id);
        return new ResponseEntity<>(expense.get(), HttpStatus.OK);
    }

    @PostMapping("/expense/add")
    public ResponseEntity<Void> addExpense(@RequestBody Expense expense){
        expenseRepository.add(expense);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/expense/update")
    public ResponseEntity<Void> updateExpense(@RequestBody Expense expense){
        expenseRepository.update(expense);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/expense/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id){
        expenseRepository.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

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
    public ResponseEntity<String> addExpense(@RequestBody Expense expense){
        boolean status = expenseRepository.add(expense);

        if (status){
            return new ResponseEntity<>("Expense added successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Expense add failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/expense/update")
    public ResponseEntity<String> updateExpense(@RequestBody Expense expense){
        boolean status = expenseRepository.update(expense);

        if (status){
            return new ResponseEntity<>("Expense updated successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Expense update failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/expense/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer id){
        boolean status = expenseRepository.delete(id);

        if (status){
            return new ResponseEntity<>("Expense deleted successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Expense delete failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

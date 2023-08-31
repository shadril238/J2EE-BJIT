package com.shadril.springbootjdbc.repository;

import com.shadril.springbootjdbc.model.Expense;

import java.util.List;
import java.util.Optional;

public interface IExpenseRepository {
    Optional<Expense> getById(Integer id);
    List<Expense> getAll();
    void add(Expense expense);
    void delete(Integer id);
    void update(Expense expense);
}

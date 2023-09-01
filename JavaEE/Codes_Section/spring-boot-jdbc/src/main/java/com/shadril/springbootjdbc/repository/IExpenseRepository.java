package com.shadril.springbootjdbc.repository;

import com.shadril.springbootjdbc.model.Expense;

import java.util.List;
import java.util.Optional;

public interface IExpenseRepository {
    Optional<Expense> getById(Integer id);
    List<Expense> getAll();
    Boolean add(Expense expense);
    Boolean delete(Integer id);
    Boolean update(Expense expense);
}

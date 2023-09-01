package com.shadril.springbootjdbc.repository;

import com.shadril.springbootjdbc.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ExpenseRepository implements IExpenseRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Expense> getById(Integer id) {
        String query = "select * from expenses where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
                Optional.of(
                        new Expense(
                                rs.getInt("id"),
                                rs.getString("category"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("amount"),
                                rs.getDate("date").toLocalDate()
                        )
                )
        );
    }

    @Override
    public List<Expense> getAll() {
        String query = "select * from expenses";
        return jdbcTemplate
                .query(query, (rs, rowNum) ->
                    new Expense(
                            rs.getInt("id"),
                            rs.getString("category"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("amount"),
                            rs.getDate("date").toLocalDate()
                    )
                );
    }

    @Override
    public Boolean add(Expense expense) {
        String query = "insert into expenses (category, name, description, amount, date) values (?, ?, ?, ?, ?)";
        int numOfRowsAffected = jdbcTemplate.update(query, expense.getCategory(), expense.getName(), expense.getDescription(), expense.getAmount(), expense.getDate());
        return numOfRowsAffected > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        String query = "delete from expenses where id = ?";
        int numOfRowsAffected = jdbcTemplate.update(query, id);
        return numOfRowsAffected > 0;
    }

    @Override
    public Boolean update(Expense expense) {
        String query = "update expenses set category = ?, name = ?, description = ?, amount = ?, date = ? where id = ?";
        int numOfRowsAffected = jdbcTemplate.update(query, expense.getCategory(), expense.getName(), expense.getDescription(), expense.getAmount(), expense.getDate(), expense.getId());
        return numOfRowsAffected > 0;
    }
}

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
    public void add(Expense expense) {
        String query = "insert into expenses (category, name, description, amount, date) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, expense.getCategory(), expense.getName(), expense.getDescription(), expense.getAmount(), expense.getDate());
    }

    @Override
    public void delete(Integer id) {
        if(getById(id).isPresent()){
            String query = "delete from expenses where id = ?";
            jdbcTemplate.update(query, id);
        }
    }

    @Override
    public void update(Expense expense) {
        if(getById(expense.getId()).isPresent()){
            String query = "update expenses set category = ?, name = ?, description = ?, amount = ?, date = ? where id = ?";
            jdbcTemplate.update(query, expense.getCategory(), expense.getName(), expense.getDescription(), expense.getAmount(), expense.getDate(), expense.getId());
        }
    }
}

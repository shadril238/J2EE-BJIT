package com.shadril.springbootjdbc.model;
import java.time.LocalDate;
public class Expense {
    private Integer id;
    private String name;
    private String category;
    private String description;
    private Double amount;
    private LocalDate date;

    public Expense() {
    }

    public Expense(Integer id, String name, String category, String description, Double amount, LocalDate date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

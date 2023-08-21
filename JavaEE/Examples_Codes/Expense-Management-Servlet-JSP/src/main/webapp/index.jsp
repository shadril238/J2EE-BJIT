<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Add Expense</title>
    <link rel="stylesheet" type="text/css" href="styles/addExpense.css">
</head>
<body>
<h1>Add Expense</h1>
<form action="expenseManager" method="post">

    <label for="category">Category:</label> <br>
    <select name="category" id="category">
        <option value="Transportation">Transportation</option>
        <option value="Food">Food</option>
        <option value="Fees">Fees</option>
        <option value="Bills">Bills</option>
        <option value="Entertainment">Entertainment</option>
    </select>

    <label for="date">Date: &nbsp </label>
    <input type="date" name="date" id="date">
    <br> <br>

    <label for="name">Expense Name:</label> <br>
    <input type="text" name="name" id="name">
    <br>

    <label for="description">Description:</label> <br>
    <textarea name="description" id="description" rows="3" cols="40"></textarea>
    <br>

    <label for="amount">Amount:</label> <br>
    <input type="number" name="amount" id="amount" step="0.01">
    <br>

    <input type="submit" value="Submit">
    <input type="button" name="expenseList" value="Expense List" onclick="location.href='expenseList'">
</form>
</body>
</html>





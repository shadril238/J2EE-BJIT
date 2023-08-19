<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expense Manager - View Expenses</title>
    <link rel="stylesheet" type="text/css" href="styles/addExpense.css">
</head>

<body>
<% List<String[]> expenseList = (List<String[]>) request.getAttribute("expenseList");%>
<h1 style="color: #333333;" align = "center">Your Spendings</h1>
<table>
    <tr>
        <th style="background-color: #333333; color: #FFFFFF;">Category</th>
        <th style="background-color: #333333; color: #FFFFFF;">Date</th>
        <th style="background-color: #333333; color: #FFFFFF;">Name</th>
        <th style="background-color: #333333; color: #FFFFFF;">Description</th>
        <th style="background-color: #333333; color: #FFFFFF;">Amount</th>
    </tr>
    <%  for (String[] expense : expenseList) { %>
        <tr>
            <td> <%=expense[0]%> </td>
            <td> <%=expense[1]%> </td>
            <td> <%=expense[2]%> </td>
            <td> <%=expense[3]%> </td>
            <td> <%=expense[4]%> </td>
        </tr>
    <% } %>

</table>
</body>
</html>
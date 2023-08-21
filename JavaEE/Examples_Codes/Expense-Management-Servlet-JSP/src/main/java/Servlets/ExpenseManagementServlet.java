package Servlets;

import DB_Utilites.ConnectJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/expenseManager")
public class ExpenseManagementServlet extends HttpServlet {

    ConnectJDBC connectJDBC = new ConnectJDBC();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setAttribute("connectJDBC", connectJDBC);
        //calling InsertServlet from here
        RequestDispatcher rd = req.getRequestDispatcher("insertServlet");
        rd.forward(req, res);
    }
}

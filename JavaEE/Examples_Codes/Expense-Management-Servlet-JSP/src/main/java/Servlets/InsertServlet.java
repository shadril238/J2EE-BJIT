package Servlets;

import DB_Utilites.ConnectJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet ("/insertServlet")
public class InsertServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Retrieve the data from req that (original data is in the form)
        String category = req.getParameter("category");
        String date = req.getParameter("date");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int amount = Integer.parseInt(req.getParameter("amount"));

        //insertion process
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            ConnectJDBC connectJDBC = (ConnectJDBC) req.getAttribute("connectJDBC");
            conn = connectJDBC.getConnection();

            String sql = "INSERT INTO expenses (category, date, name, description, amount) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, category);
            statement.setString(2, date);
            statement.setString(3, name);
            statement.setString(4, description);
            statement.setInt(5, amount);
            statement.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            System.out.println("Failed to insert data");
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Do nothing
                }
            }
        }
        //calling expenseListServlet from here
        RequestDispatcher rd = req.getRequestDispatcher("/expenseList");
        rd.forward(req, res);
    }
}

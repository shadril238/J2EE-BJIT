package Servlets;

import DB_Utilites.ConnectJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/expenseList")
public class ExpenseListServletAndFetch extends HttpServlet {

    ConnectJDBC connectJDBC = new ConnectJDBC();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<String[]> expenseList = new ArrayList<String[]>();

        try {
            //ConnectJDBC connectJDBC = (ConnectJDBC) req.getAttribute("connectJDBC");
            conn = connectJDBC.getConnection();

            String sql = "SELECT * FROM expenses ORDER BY date";
            statement = conn.prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {
                String[] expense = new String[5];
                expense[0] = result.getString("category");
                expense[1] = result.getString("date");
                expense[2] = result.getString("name");
                expense[3] = result.getString("description");
                expense[4] = Integer.toString(result.getInt("amount"));

                expenseList.add(expense);
            }
            req.setAttribute("expenseList", expenseList);
            RequestDispatcher rd = req.getRequestDispatcher("outputPage.jsp");
            rd.forward(req, res);
        } catch (SQLException e) {
            System.out.println("Failed to retrieve data");
            throw new RuntimeException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }
}

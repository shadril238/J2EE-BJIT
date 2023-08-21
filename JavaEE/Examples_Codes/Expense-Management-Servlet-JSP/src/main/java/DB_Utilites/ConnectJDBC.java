package DB_Utilites;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class ConnectJDBC {

    String url = "jdbc:mysql://localhost/expense_manager";
    String user = "root";
    String pass = "";

    Connection connection;
    public ConnectJDBC()  {
        this.connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("successfully created Connection object and connected jdbc");
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

//    Statement statement = connection.createStatement();

}
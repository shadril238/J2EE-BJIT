package bjit.limited;

import jdk.jshell.execution.Util;

import java.sql.*;

public class DBOperations {
    Connection conn;
    public void doConnectDB(){
        String connectionStr = "jdbc:mysql://localhost:3306/" + Utils.DB_NAME;
        String userName = "root";
        String password = "";
        try{
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchClientData(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Utils.TABLE_NAME);
            while (rs.next()){
                String clientName = rs.getString(Utils.COLUMN_CLIENT_NAME);
                String clientEmail = rs.getString(Utils.COLUMN_CLIENT_EMAIL);
                String companyName = rs.getString(Utils.COLUMN_CLIENT_COMPANYNAME);
                System.out.println("Client Name : " + clientName + ", Client Email : " + clientEmail + ", Company Name : " + companyName);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

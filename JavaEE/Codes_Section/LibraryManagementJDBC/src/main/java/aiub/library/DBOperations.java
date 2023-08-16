package aiub.library;

import jdk.jshell.execution.Util;

import java.sql.*;
public class DBOperations {
    Connection conn;
    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/" + Utils.DB_NAME;
        String userName = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Fetch all books data
    public void fetchBooksData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Utils.TABLE_BOOKS);

            while (rs.next()) {
                String bookTitle = rs.getString(Utils.COLUMN_BOOK_TITLE);
                String bookAuthor = rs.getString(Utils.COLUMN_BOOK_AUTHOR);
                String bookGenre = rs.getString(Utils.COLUMN_BOOK_GENRE);
                int bookPublicationYear = rs.getInt(Utils.COLUMN_BOOK_PUBLICATIONYEAR);
                System.out.println("Book Title: " + bookTitle + ", Author: " + bookAuthor + ", Genre: "+ bookGenre + ", Publication Year: " + bookPublicationYear);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    // Fetch all members data
    public void fetchMembersData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Utils.TABLE_MEMBERS);

            while (rs.next()) {
                String memberName = rs.getString(Utils.COLUMN_MEMBER_NAME);
                String memberEmail = rs.getString(Utils.COLUMN_MEMBER_EMAIL);
                String memberType = rs.getString(Utils.COLUMN_MEMBER_TYPE);
                System.out.println("Member Name: " + memberName + ", Email: " + memberEmail + ", Type: "+ memberType);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    // Fetch all members data who borrow at least one book from the library
    public void fetchBorrowersData(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + Utils.TABLE_MEMBERS + "." +Utils.COLUMN_MEMBER_NAME + ","
                    + Utils.TABLE_MEMBERS + "." + Utils.COLUMN_MEMBER_TYPE + ","
                    + Utils.TABLE_BOOKS + "." + Utils.COLUMN_BOOK_TITLE + ","
                    + Utils.TABLE_BOOKS + "." + Utils.COLUMN_BOOK_GENRE + ","
                    + Utils.TABLE_TRANSACTIONS + "." + Utils.COLUMN_TRANSACTION_TRANSDATE
                    + " FROM " + Utils.TABLE_TRANSACTIONS
                    + " LEFT JOIN " + Utils.TABLE_BOOKS + " ON "
                    + Utils.TABLE_BOOKS + "." + Utils.COLUMN_BOOK_ID + " = " + Utils.TABLE_TRANSACTIONS + "." + Utils.COLUMN_TRANSACTION_BOOKID
                    + " LEFT JOIN " + Utils.TABLE_MEMBERS + " ON "
                    + Utils.TABLE_MEMBERS + "." + Utils.COLUMN_MEMBER_ID + " = " + Utils.TABLE_TRANSACTIONS + "." + Utils.COLUMN_TRANSACTION_MEMBERID
                    + " WHERE " + Utils.TABLE_TRANSACTIONS + "." + Utils.COLUMN_TRANSACTION_TRANSTYPE + "= 'Issue'");

            while (rs.next()) {
                String borrowerName = rs.getString(Utils.COLUMN_MEMBER_NAME);
                String borrowerType = rs.getString(Utils.COLUMN_MEMBER_TYPE);
                String bookTitle = rs.getString(Utils.COLUMN_BOOK_TITLE);
                String bookGenre = rs.getString(Utils.COLUMN_BOOK_GENRE);
                String transactionDate = rs.getString(Utils.COLUMN_TRANSACTION_TRANSDATE);

                System.out.println("Borrower's Name: " + borrowerName + ", Borrower's Type: " + borrowerType
                        + ", Book Title: " + bookTitle + ", Book Genre: " + bookGenre + ", Borrow Date: " + transactionDate);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    // Fetch total number of books group by genre
    public void fetchTotalNumberOfBooksByGenre(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + Utils.COLUMN_BOOK_GENRE
                    + ", SUM(" + Utils.COLUMN_BOOK_QUANTITY + ") AS 'total_count' FROM " + Utils.TABLE_BOOKS + " GROUP BY " + Utils.COLUMN_BOOK_GENRE);

            while (rs.next()) {
                String bookGenre = rs.getString(Utils.COLUMN_BOOK_GENRE);
                int totalCount = rs.getInt("total_count");

                System.out.println("Book Genre: " + bookGenre + ", Total Book Count: " + totalCount);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    // Total book count group by Transaction Type (Issue/Return)
    public void fetchBooksCountByTransactions(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + Utils.TABLE_TRANSACTIONS + "." +Utils.COLUMN_TRANSACTION_TRANSTYPE
                    + ", COUNT(*) AS 'total_count'"
                    + " FROM " + Utils.TABLE_TRANSACTIONS
                    + " LEFT JOIN " + Utils.TABLE_BOOKS + " ON "
                    + Utils.TABLE_BOOKS + "." + Utils.COLUMN_BOOK_ID + " = " + Utils.TABLE_TRANSACTIONS + "." + Utils.COLUMN_TRANSACTION_BOOKID
                    + " GROUP BY " + Utils.COLUMN_TRANSACTION_TRANSTYPE);

            while (rs.next()) {
                String transType = rs.getString(Utils.COLUMN_TRANSACTION_TRANSTYPE);
                int totalBookCount = rs.getInt("total_count");
                System.out.println("Transaction type: " + transType + ", Total book count: " + totalBookCount);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

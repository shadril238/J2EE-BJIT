package aiub.library;

public class Main {
    public static void main(String[] args) {
        DBOperations dbObj = new DBOperations();
        dbObj.doConnectDB();
        // All books data
        System.out.println("All books data - ");
        dbObj.fetchBooksData();
        // All members data
        System.out.println("\nAll members data - ");
        dbObj.fetchMembersData();
        // All borrowers data who borrow at least one books from the library
        System.out.println("\nAll borrowers data - ");
        dbObj.fetchBorrowersData();
    }
}

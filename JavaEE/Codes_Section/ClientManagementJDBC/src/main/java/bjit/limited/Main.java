package bjit.limited;

public class Main {
    public static void main(String[] args) {
        DBOperations dbObj = new DBOperations();
        dbObj.doConnectDB();
        dbObj.fetchClientData();
    }
}

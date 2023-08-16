package aiub.library;

public class Utils {
    public static final String DB_NAME = "aiub_lms";
    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_MEMBERS = "members";
    public static final String TABLE_TRANSACTIONS = "transactions";
    // Table Books
    public static final String COLUMN_BOOK_ID = "id";
    public static final String COLUMN_BOOK_TITLE = "title";
    public static final String COLUMN_BOOK_AUTHOR = "author";
    public static final String COLUMN_BOOK_GENRE = "genre";
    public static final String COLUMN_BOOK_PUBLICATIONYEAR = "publicationYear";
    public static final String COLUMN_BOOK_QUANTITY = "quantity";
    // Table Members
    public static final String COLUMN_MEMBER_ID = "id";
    public static final String COLUMN_MEMBER_NAME = "name";
    public static final String COLUMN_MEMBER_EMAIL = "email";
    public static final String COLUMN_MEMBER_TYPE = "type";
    // Table Transactions
    public static final String COLUMN_TRANSACTION_ID = "id";
    public static final String COLUMN_TRANSACTION_MEMBERID = "memberId";
    public static final String COLUMN_TRANSACTION_BOOKID = "bookId";
    public static final String COLUMN_TRANSACTION_TRANSTYPE = "transType";
    public static final String COLUMN_TRANSACTION_TRANSDATE= "transDate";
}

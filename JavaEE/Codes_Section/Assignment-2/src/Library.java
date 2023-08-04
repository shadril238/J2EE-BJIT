import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
public class Library {
    private ArrayList<Book> booksList;
    public Library(){
        booksList = new ArrayList<>();
    }
    public void addBook(Book book){
        this.booksList.add(book);
    }
    public ArrayList<Book> getBooks(BookPredicate predicate){
        ArrayList<Book> getBooksList = new ArrayList<>();
        for(Book book : booksList){
            if(predicate.test(book)){
                getBooksList.add(book);
            }
        }
        return getBooksList;
    }
    public void removeBooks(BookPredicate predicate){
        booksList.removeIf(predicate::test);
    }
    public void displayBooks(ArrayList<Book> books){
        for (Book book : books){
            System.out.println("Book Title : " + book.getTitle() + ", Book Author : " + book.getAuthor() + ", Book Genre : " + book.getGenre() + ", Publish Year : " + book.getYearPublished());
        }
    }
    public void sortBooks(Comparator<Book> comparator) {
        Collections.sort(booksList, comparator);
    }
}

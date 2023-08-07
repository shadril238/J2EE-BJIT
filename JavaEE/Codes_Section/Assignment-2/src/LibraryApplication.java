import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LibraryApplication {
    public static void main(String[] args) {
        Library library = new Library();

        // Add multiple books
        library.addBook(new Book("Coding for Beginners","John Smith","Technology", 2016));
        library.addBook(new Book("The Forgotten Kingdom","Emily Williams","Mystery", 2018));
        library.addBook(new Book("Mind Games","David Anderson","Psychology", 2020));
        library.addBook(new Book("The Lost City","Sarah Thompson","Adventure", 2017));
        library.addBook(new Book("The Art of Cooking","Julia Adams","Cooking", 2022));
        library.addBook(new Book("Whispers in the Wind","Michael Johnson","Mystery", 2019));

        // Filter books by a specific author
        BookPredicate authorPredicate = book -> book.getAuthor().equals("John Smith");
        ArrayList<Book> booksByAuthor = library.getBooks(authorPredicate);
        System.out.println("Books by specific author - ");
        library.displayBooks(booksByAuthor);

        // Filter books published after a given year
        BookPredicate yearPredicate = book -> book.getYearPublished() > 2019;
        ArrayList<Book> booksPublishedAfterGivenYear = library.getBooks(yearPredicate);
        System.out.println("Books published after a given year - ");
        library.displayBooks(booksPublishedAfterGivenYear);

        // Filter books of a particular genre
        BookPredicate genrePredicate = book -> book.getGenre().equals("Mystery");
        ArrayList<Book> booksByGenre = library.getBooks(genrePredicate);
        System.out.println("Books of a particular genre - ");
        library.displayBooks(booksByGenre);

        // Remove books based on a specific condition.
        library.removeBooks(book -> book.getYearPublished() > 2019);
        System.out.println("Remaining Books - ");
        library.displayBooks(library.getBooks(book -> true));

        // Sorting books by year published, then by title
        library.sortBooks(Comparator.comparing(Book::getYearPublished).thenComparing(Book::getTitle));
        System.out.println("Sorted by Year Published, then by Title:");
        library.displayBooks(library.getBooks(book -> true));
    }
}

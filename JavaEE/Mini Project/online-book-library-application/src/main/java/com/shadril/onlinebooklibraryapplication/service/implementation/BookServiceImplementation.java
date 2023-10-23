package com.shadril.onlinebooklibraryapplication.service.implementation;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.BorrowBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import com.shadril.onlinebooklibraryapplication.exception.BookAlreadyExistsException;
import com.shadril.onlinebooklibraryapplication.exception.BookNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.UserNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.UserNotValidException;
import com.shadril.onlinebooklibraryapplication.repository.BookRepository;
import com.shadril.onlinebooklibraryapplication.repository.BorrowBookRepository;
import com.shadril.onlinebooklibraryapplication.repository.UserRepository;
import com.shadril.onlinebooklibraryapplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class BookServiceImplementation implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowBookRepository borrowBookRepository;

    @Override
    public void create(Book book) throws BookAlreadyExistsException {
        if(bookRepository.findByTitle(book.getTitle()).isPresent()){
            throw new BookAlreadyExistsException("The book you want to add already exist.");
        }
        bookRepository.save(book);
    }

    @Override
    public void update(Book book)
            throws BookAlreadyExistsException, BookNotFoundException {
        if(bookRepository.findByTitle(book.getTitle()).isPresent()){
            throw new BookAlreadyExistsException("The book you want to update already exist.");
        }
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if(existingBook.isPresent()) {
            bookRepository.save(book);
        }
        else{
            throw new BookNotFoundException("The book you want to update not found.");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().filter(Book::getIsActive).toList();
    }

    @Override
    public void delete(Book book)
            throws BookNotFoundException{
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if(existingBook.isPresent() && existingBook.get().getIsActive().equals(true) && existingBook.get().getStatus().equals("AVAILABLE")) {
            book.setIsActive(false);
            bookRepository.save(book);
        }
        else {
            throw new BookNotFoundException("The book you requested not found.");
        }

    }

    @Override
    public void borrowBook(Long bookId)
        throws UserNotFoundException, BookNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Long userId = user.get().getId();

        Optional<User> userFromId = userRepository.findById(userId);
        Optional<Book> bookFromId = bookRepository.findById(bookId);

        if(userFromId.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (bookFromId.isEmpty() || !bookFromId.get().getIsActive()) {
            throw new BookNotFoundException("Book not found");
        }
        if(Objects.equals(bookFromId.get().getStatus(), "BORROWED")) {
            throw new BookNotFoundException("Book already borrowed...currently unavailable");
        }

        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setBook(bookFromId.get());
        borrowBook.setUser(userFromId.get());
        borrowBook.setBorrowDate(LocalDate.now());
        borrowBook.setDueDate(LocalDate.now().plusDays(7));
        bookFromId.get().setStatus("BORROWED");

        borrowBookRepository.save(borrowBook);
        bookRepository.save(bookFromId.get());
    }

    @Override
    public void returnBook(Long bookId)
            throws UserNotFoundException, BookNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Long userId = user.get().getId();

        Optional<User> userFromId = userRepository.findById(userId);
        Optional<Book> bookFromId = bookRepository.findById(bookId);

        if(userFromId.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (bookFromId.isEmpty() || !bookFromId.get().getIsActive()) {
            throw new BookNotFoundException("Book not found");
        }
        if(!Objects.equals(bookFromId.get().getStatus(), "BORROWED")) {
            throw new BookNotFoundException("Book is not currently borrowed");
        }

        List<BorrowBook> borrowRecord = borrowBookRepository.findNotReturnedBooksForUser(userFromId.get());
        if (borrowRecord.isEmpty()) {
            throw new BookNotFoundException("No matching borrow record found");
        }

        BorrowBook borrowBook = borrowRecord.stream().filter(b -> b.getBook().getId().equals(bookId)).findFirst().orElseThrow(() -> new BookNotFoundException("No matching borrow record found"));
        bookFromId.get().setStatus("AVAILABLE");
        bookRepository.save(bookFromId.get());

        borrowBook.setReturnDate(LocalDate.now());
        borrowBookRepository.save(borrowBook);
    }

    @Override
    public List<Book> retrievedBooks(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Long currUserId = user.orElseThrow(() -> new UserNotFoundException("User not found")).getId();
        String currUserRole = user.get().getRole();

        Optional<User> userFromId = userRepository.findById(userId);
        if (userFromId.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (currUserRole.equals("CUSTOMER") && !currUserId.equals(userId)) {
            throw new UserNotValidException("Not a valid customer...");
        }

        List<BorrowBook> borrowBooks = borrowBookRepository.findByUser(userFromId.get());
        return borrowBooks.stream().map(BorrowBook::getBook).collect(Collectors.toList());
    }


    @Override
    public List<Book> retrievedBorrowedBooks(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Long currUserId = user.orElseThrow(() -> new UserNotFoundException("User not found")).getId();
        String currUserRole = user.get().getRole();

        Optional<User> userFromId = userRepository.findById(userId);
        if (userFromId.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (currUserRole.equals("CUSTOMER") && !currUserId.equals(userId)) {
            throw new UserNotValidException("Not a valid customer...");
        }
        List<BorrowBook> borrowBooks = borrowBookRepository.findByUserAndReturnDateIsNull(userFromId.get());
        return borrowBooks.stream().map(BorrowBook::getBook).collect(Collectors.toList());
    }

    @Override
    public List<BorrowBook> borrowHistory(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Long currUserId = user.orElseThrow(() -> new UserNotFoundException("User not found")).getId();
        String currUserRole = user.get().getRole();

        Optional<User> userFromId = userRepository.findById(userId);
        if (userFromId.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (currUserRole.equals("CUSTOMER") && !currUserId.equals(userId)) {
            throw new UserNotValidException("Not a valid customer...");
        }

        return borrowBookRepository.findByUser(userFromId.get());
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

}

package com.shadril.onlinebooklibraryapplication.service;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.BorrowBook;
import com.shadril.onlinebooklibraryapplication.exception.BookAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void create(Book book);
    void update(Book book);
    List<Book> getAllBooks();
    void delete(Book book);
    void borrowBook(Long bookId);
    void returnBook(Long bookId);
    List<Book> retrievedBooks(Long userId);
    List<Book> retrievedBorrowedBooks(Long userId);
    List<BorrowBook> borrowHistory(Long userId);
    Optional<Book> getBookById(Long bookId);

}

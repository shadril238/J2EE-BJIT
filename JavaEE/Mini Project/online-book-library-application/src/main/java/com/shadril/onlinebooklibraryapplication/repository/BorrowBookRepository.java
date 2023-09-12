package com.shadril.onlinebooklibraryapplication.repository;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.BorrowBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    Optional<BorrowBook> findByBookAndUser(Book book, User user);
    List<BorrowBook> findByUser(User user);
    List<BorrowBook> findByUserAndReturnDateIsNull(User user);
}

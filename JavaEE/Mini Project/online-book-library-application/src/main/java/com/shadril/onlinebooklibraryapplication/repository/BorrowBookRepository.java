package com.shadril.onlinebooklibraryapplication.repository;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.BorrowBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    @Query("SELECT bb FROM BorrowBook bb WHERE bb.user = :user AND bb.returnDate IS NULL")
    List<BorrowBook> findNotReturnedBooksForUser(@Param("user") User user);

    List<BorrowBook> findByUser(User user);
    List<BorrowBook> findByUserAndReturnDateIsNull(User user);
}

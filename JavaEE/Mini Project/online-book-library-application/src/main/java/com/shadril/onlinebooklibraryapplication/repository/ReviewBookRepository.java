package com.shadril.onlinebooklibraryapplication.repository;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.ReviewBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewBookRepository extends JpaRepository<ReviewBook, Long> {
    Optional<ReviewBook> findByUserAndBook(User user, Book book);
    List<ReviewBook> findByBook(Book book);
    Optional<ReviewBook> findByIdAndBookAndUser(Long reviewId, Book book, User user);

}

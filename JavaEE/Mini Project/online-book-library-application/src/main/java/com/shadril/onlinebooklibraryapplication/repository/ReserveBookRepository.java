package com.shadril.onlinebooklibraryapplication.repository;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.ReserveBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReserveBookRepository extends JpaRepository<ReserveBook, Long> {
    List<ReserveBook> findByBookAndStatus(Book book, String status);
    Optional<ReserveBook> findByUserAndBook(User user, Book book);
}

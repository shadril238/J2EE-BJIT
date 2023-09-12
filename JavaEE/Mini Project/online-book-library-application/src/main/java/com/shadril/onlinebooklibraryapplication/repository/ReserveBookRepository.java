package com.shadril.onlinebooklibraryapplication.repository;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.ReserveBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReserveBookRepository extends JpaRepository<ReserveBook, Long> {
    List<ReserveBook> findByBookAndStatus(Book book, String status);

    @Query("SELECT rb FROM ReserveBook rb WHERE rb.id = :userId AND rb.book.id = :bookId")
    Optional<ReserveBook> findByUserIdAndBookId(
            @Param("userId") Long userId,
            @Param("bookId") Long bookId
    );
}


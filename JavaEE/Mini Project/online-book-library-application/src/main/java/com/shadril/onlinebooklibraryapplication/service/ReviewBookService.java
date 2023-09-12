package com.shadril.onlinebooklibraryapplication.service;

import com.shadril.onlinebooklibraryapplication.entity.ReviewBook;

import java.util.List;

public interface ReviewBookService {

    void createBookReview(Long bookId, ReviewBook reviewBook);
    List<ReviewBook> allBookReviews(Long bookId);
    void updateBookReview(Long bookId, Long reviewId, ReviewBook reviewBook);
    void deleteBookReview(Long bookId, Long reviewId);
}

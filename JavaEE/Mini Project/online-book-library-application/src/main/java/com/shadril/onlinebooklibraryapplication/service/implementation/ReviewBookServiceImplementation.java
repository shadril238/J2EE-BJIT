package com.shadril.onlinebooklibraryapplication.service.implementation;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.ReviewBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import com.shadril.onlinebooklibraryapplication.exception.BookNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.ReviewAlreadyExistsException;
import com.shadril.onlinebooklibraryapplication.exception.ReviewNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.UserNotFoundException;
import com.shadril.onlinebooklibraryapplication.repository.BookRepository;
import com.shadril.onlinebooklibraryapplication.repository.ReviewBookRepository;
import com.shadril.onlinebooklibraryapplication.repository.UserRepository;
import com.shadril.onlinebooklibraryapplication.service.ReviewBookService;
import com.shadril.onlinebooklibraryapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class ReviewBookServiceImplementation implements ReviewBookService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewBookRepository reviewBookRepository;
    @Override
    public void createBookReview(Long bookId, ReviewBook review)
            throws UserNotFoundException, BookNotFoundException, ReviewAlreadyExistsException{
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
        Optional<ReviewBook> existingReview = reviewBookRepository.findByUserAndBook(userFromId.get(), bookFromId.get());
        if (existingReview.isPresent()) {
            throw new ReviewAlreadyExistsException("A review already exists for this book");
        }

        ReviewBook reviewBook = new ReviewBook();
        reviewBook.setUser(userFromId.get());
        reviewBook.setBook(bookFromId.get());
        reviewBook.setReview(review.getReview());
        reviewBook.setRating(review.getRating());
        reviewBook.setDate(LocalDate.now());

        reviewBookRepository.save(reviewBook);
    }

    @Override
    public List<ReviewBook> allBookReviews(Long bookId)
            throws BookNotFoundException{
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty() || !bookOptional.get().getIsActive()){
            throw new BookNotFoundException("Book Not Found");
        }
        return reviewBookRepository.findByBook(bookOptional.get());
    }

    @Override
    public void updateBookReview(Long bookId, Long reviewId, ReviewBook reviewBook)
            throws UserNotFoundException, BookNotFoundException, ReviewNotFoundException {
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

        Optional<ReviewBook> reviewBookOptional = reviewBookRepository.findByIdAndBookAndUser(reviewId, bookFromId.get(), userFromId.get());
        if (reviewBookOptional.isEmpty()){
            throw new ReviewNotFoundException("Review not found.");
        }


        reviewBookOptional.get().setUser(userFromId.get());
        reviewBookOptional.get().setBook(bookFromId.get());
        reviewBookOptional.get().setReview(reviewBook.getReview());
        reviewBookOptional.get().setDate(LocalDate.now());

        reviewBookRepository.save(reviewBookOptional.get());
    }

    @Override
    public void deleteBookReview(Long bookId, Long reviewId)
            throws UserNotFoundException, BookNotFoundException, ReviewNotFoundException {
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

        Optional<ReviewBook> reviewBookOptional = reviewBookRepository.findByIdAndBookAndUser(reviewId, bookFromId.get(), userFromId.get());
        if (reviewBookOptional.isEmpty()){
            throw new ReviewNotFoundException("Review not found.");
        }

        reviewBookRepository.delete(reviewBookOptional.get());
    }
}

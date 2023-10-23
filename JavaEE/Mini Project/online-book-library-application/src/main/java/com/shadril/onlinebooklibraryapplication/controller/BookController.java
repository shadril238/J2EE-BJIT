package com.shadril.onlinebooklibraryapplication.controller;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.ReviewBook;
import com.shadril.onlinebooklibraryapplication.exception.BookAlreadyExistsException;
import com.shadril.onlinebooklibraryapplication.exception.BookNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.ReviewNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.UserNotFoundException;
import com.shadril.onlinebooklibraryapplication.service.BookService;
import com.shadril.onlinebooklibraryapplication.service.ReserveBookService;
import com.shadril.onlinebooklibraryapplication.service.ReviewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReviewBookService reviewBookService;

    @Autowired
    private ReserveBookService reserveBookService;

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book)
            throws BookAlreadyExistsException {
        bookService.create(book);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody Book book)
            throws BookAlreadyExistsException, BookNotFoundException {
        bookService.update(book);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody Book book)
        throws BookNotFoundException{
        bookService.delete(book);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId)
            throws BookNotFoundException {
        return new ResponseEntity<>(bookService.getBookById(bookId).get(), HttpStatus.OK);
    }
    @GetMapping("/{bookId}/borrow")
    public ResponseEntity<String> borrowBooks(@PathVariable Long bookId)
            throws UserNotFoundException, BookNotFoundException {
        bookService.borrowBook(bookId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/{bookId}/return")
    public ResponseEntity<String> returnBooks(@PathVariable Long bookId)
            throws UserNotFoundException, BookNotFoundException {
        bookService.returnBook(bookId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/{bookId}/reviews/create")
    public ResponseEntity<String> createBookReview(@PathVariable Long bookId, @RequestBody ReviewBook reviewBook)
            throws UserNotFoundException, BookNotFoundException {
        reviewBookService.createBookReview(bookId, reviewBook);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<List<ReviewBook>> bookReview(@PathVariable Long bookId)
            throws UserNotFoundException, BookNotFoundException{
        return new ResponseEntity<>(reviewBookService.allBookReviews(bookId), HttpStatus.OK);
    }

    @PostMapping("/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<String> updateBookReview(@PathVariable Long bookId, @PathVariable Long reviewId, @RequestBody ReviewBook reviewBook)
            throws UserNotFoundException, BookNotFoundException, ReviewNotFoundException {
        reviewBookService.updateBookReview(bookId, reviewId, reviewBook);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}/reviews/{reviewId}/delete")
    public ResponseEntity<String> deleteBookReview(@PathVariable Long bookId, @PathVariable Long reviewId)
            throws UserNotFoundException, BookNotFoundException, ReviewNotFoundException{
        reviewBookService.deleteBookReview(bookId, reviewId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/{bookId}/reserve")
    public ResponseEntity<String> reserveBook(@PathVariable Long bookId){
        reserveBookService.reserveBook(bookId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/{bookId}/cancel-reservation")
    public ResponseEntity<String> cancelReservation(@PathVariable Long bookId){
        reserveBookService.cancelReserveBook(bookId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}

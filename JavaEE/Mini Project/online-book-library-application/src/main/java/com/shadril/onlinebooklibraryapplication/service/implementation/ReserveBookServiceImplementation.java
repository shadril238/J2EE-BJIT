package com.shadril.onlinebooklibraryapplication.service.implementation;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.BorrowBook;
import com.shadril.onlinebooklibraryapplication.entity.ReserveBook;
import com.shadril.onlinebooklibraryapplication.entity.User;
import com.shadril.onlinebooklibraryapplication.exception.*;
import com.shadril.onlinebooklibraryapplication.repository.BookRepository;
import com.shadril.onlinebooklibraryapplication.repository.BorrowBookRepository;
import com.shadril.onlinebooklibraryapplication.repository.ReserveBookRepository;
import com.shadril.onlinebooklibraryapplication.repository.UserRepository;
import com.shadril.onlinebooklibraryapplication.service.ReserveBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class ReserveBookServiceImplementation implements ReserveBookService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowBookRepository borrowBookRepository;
    @Autowired
    private ReserveBookRepository reserveBookRepository;
    @Override
    public void reserveBook(Long bookId)
            throws UserNotFoundException, BookAlreadyReservedException,
            BookNotFoundException, BookAlreadyExistsException{
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
        if (Objects.equals(bookFromId.get().getStatus(), "AVAILABLE")) {
            throw new BookAlreadyExistsException("This book is already available, you can borrow this!");
        }
        Optional<ReserveBook> reserveBook = reserveBookRepository.findByUserIdAndBookId(userFromId.get().getId(),bookFromId.get().getId());
        if (reserveBook.isPresent() && reserveBook.get().getStatus().equals("RESERVED")){
            throw new BookAlreadyReservedException("You already reserved the book before!");
        }

        List<BorrowBook> borrowBook = borrowBookRepository.findByUserAndReturnDateIsNull(userFromId.get());
        if (!borrowBook.isEmpty()){
            throw new BookAlreadyBorrowedException("Book already borrowed by you");
        }

        ReserveBook reserveBookToAdd = new ReserveBook();
        reserveBookToAdd.setUser(userFromId.get());
        reserveBookToAdd.setBook(bookFromId.get());
        reserveBookToAdd.setReserveDate(LocalDate.now());
        reserveBookToAdd.setStatus("RESERVED");

        reserveBookRepository.save(reserveBookToAdd);
    }

    @Override
    public void cancelReserveBook(Long bookId)
            throws UserNotFoundException,
            BookNotFoundException, BookReserveNotFoundException{
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
        Optional<ReserveBook> reserveBook = reserveBookRepository.findByUserIdAndBookId(userFromId.get().getId(),bookFromId.get().getId());
        if (reserveBook.isEmpty() || reserveBook.get().getStatus().equals("CANCEL RESERVATION")){
            throw new BookReserveNotFoundException("No reserve found...");
        }
        reserveBook.get().setStatus("CANCEL RESERVATION");

        reserveBookRepository.save(reserveBook.get());
    }
}

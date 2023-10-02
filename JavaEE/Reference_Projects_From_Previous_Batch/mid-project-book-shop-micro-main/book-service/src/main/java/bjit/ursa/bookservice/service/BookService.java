package bjit.ursa.bookservice.service;

import bjit.ursa.bookservice.model.*;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<APIResponse<?>> addBooks(BookCreateRequest bookCreateRequest);

    ResponseEntity<APIResponse<?>> getAllBooks();

    ResponseEntity<APIResponse<?>> updateBooks(UpdateBookRequest updateBookRequest);

    ResponseEntity<APIResponse<?>> deleteBookById(Long bookId);

    ResponseEntity<APIResponse<?>> getBookById(Long bookId);

    ResponseEntity<APIResponse<?>> buyBook( BuyBookRequest buyBookRequest);
}
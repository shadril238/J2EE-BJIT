package bjit.ursa.bookservice.controller;

import bjit.ursa.bookservice.model.*;
import bjit.ursa.bookservice.service.BookService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service")
public class BookController {
    private final BookService bookService;

    @CircuitBreaker(name = "book_inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "book_inventory")
    @Retry(name = "book_inventory")
    @PostMapping("/create")
    @CacheEvict(value = "allBooks", allEntries = true)
    public CompletableFuture<ResponseEntity<APIResponse<?>>> createBooks(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        return CompletableFuture.supplyAsync(() -> bookService.addBooks(bookCreateRequest));
    }

    @CircuitBreaker(name = "book_inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "book_inventory")
    @Retry(name = "book_inventory")
    @DeleteMapping("/delete/{bookId}")
    public CompletableFuture<ResponseEntity<APIResponse<?>>> deleteBookById(@PathVariable Long bookId) {
        return CompletableFuture.supplyAsync(() -> bookService.deleteBookById(bookId));
    }

    @CircuitBreaker(name = "book_inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "book_inventory")
    @Retry(name = "book_inventory")
    @PutMapping("/update")
    @CacheEvict(value = "allBooks", allEntries = true)
    public CompletableFuture<ResponseEntity<APIResponse<?>>> updateBook(@Valid @RequestBody UpdateBookRequest updateBookRequest) {
        return CompletableFuture.supplyAsync(() -> bookService.updateBooks(updateBookRequest));
    }

    @CircuitBreaker(name = "book_inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "book_inventory")
    @Retry(name = "book_inventory")
    @GetMapping("/book/all")
    @Cacheable("allBooks")
    public CompletableFuture<ResponseEntity<APIResponse<?>>> getAllBooks() {
        return CompletableFuture.supplyAsync(bookService::getAllBooks);
    }

    @CircuitBreaker(name = "book_inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "book_inventory")
    @Retry(name = "book_inventory")
    @GetMapping("/book/id/{bookId}")
    public CompletableFuture<ResponseEntity<APIResponse<?>>> getBookById(@PathVariable Long bookId) {
        return CompletableFuture.supplyAsync(() -> bookService.getBookById(bookId));
    }

    @CircuitBreaker(name = "book_inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "book_inventory")
    @Retry(name = "book_inventory")
    @PostMapping("/book/buy")
    @CacheEvict(value = "allBooks", allEntries = true)
    public CompletableFuture<ResponseEntity<APIResponse<?>>> buyBook(@Valid @RequestBody BuyBookRequest buyBookRequest) {
        return CompletableFuture.supplyAsync(() -> bookService.buyBook(buyBookRequest));
    }

    public CompletableFuture<ResponseEntity<APIResponse<?>>> fallbackMethod(RuntimeException e) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage())));
    }
}
package bjit.ursa.bookservice.service.impl;

import bjit.ursa.bookservice.entity.BookEntity;
import bjit.ursa.bookservice.exception.BookServiceException;
import bjit.ursa.bookservice.model.*;
import bjit.ursa.bookservice.repository.BookRepository;
import bjit.ursa.bookservice.service.BookService;
import brave.Span;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;
    private final Tracer tracer;
    @Value("${INTERNAL_KEY}")
    private String INTERNAL_KEY;
    private final HttpHeaders headers = new HttpHeaders();

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> addBooks(BookCreateRequest bookCreateRequest) {
        String authorName = bookCreateRequest.getAuthorName();
        String bookName = bookCreateRequest.getBookName();
        String genre = bookCreateRequest.getGenre();
        InventoryModel inventoryModel = InventoryModel.builder()
                .bookPrice(bookCreateRequest.getPrice())
                .bookQuantity(bookCreateRequest.getQuantity())
                .build();
        try {
            if (bookRepository.findByBookNameAndAuthorName(bookName, authorName).isPresent()) {
                throw new BookServiceException("A book with the same name and author already exists.");
            }
            BookEntity book = BookEntity.builder()
                    .bookName(bookName)
                    .authorName(authorName)
                    .genre(genre)
                    .build();
            //saving to book db
            bookRepository.save(book);
            headers.set("ALLOWED", INTERNAL_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<InventoryModel> requestEntity = new HttpEntity<>(inventoryModel, headers);
            //api call to inventory service
            APIResponseWithInventory response = restTemplate.exchange("http://localhost:8080/inventory-service/update/" + book.getBook_id(), HttpMethod.POST
                    , requestEntity,
                    APIResponseWithInventory.class).getBody();
            if (response.getData() == null) {
                throw new BookServiceException("from inventory " + response.getError_message());
            }
            BookModel bookModel = BookModel.builder()
                    .bookId(book.getBook_id())
                    .bookName(bookCreateRequest.getBookName())
                    .authorName(bookCreateRequest.getAuthorName())
                    .genre(bookCreateRequest.getGenre())
                    .price(bookCreateRequest.getPrice())
                    .quantity(bookCreateRequest.getQuantity())
                    .build();
            APIResponse<BookModel> apiResponse = new APIResponse<BookModel>(bookModel, null);
            // Return the ResponseEntity with the APIResponse
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                throw new BookServiceException("Inventory Service is not available");
            }
            throw new BookServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> updateBooks(UpdateBookRequest updateBookRequest) {
        try {
            Optional<BookEntity> optionalBook = bookRepository.findById(updateBookRequest.getBookId());
            if (optionalBook.isPresent()) {
                BookEntity book = optionalBook.get();
                // Update the book entity with the new values from the request model
                book.setBookName(updateBookRequest.getBookName());
                book.setAuthorName(updateBookRequest.getAuthorName());
                book.setGenre(updateBookRequest.getGenre());
                // Save the updated book entity
                BookEntity updatedBook = bookRepository.save(book);
                InventoryModel inventoryModel = InventoryModel.builder()
                        .bookId(updatedBook.getBook_id())
                        .bookQuantity(updateBookRequest.getQuantity())
                        .bookPrice(updateBookRequest.getPrice()).build();
                headers.set("ALLOWED", INTERNAL_KEY);
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<InventoryModel> requestEntity = new HttpEntity<>(inventoryModel, headers);
                APIResponseWithInventory response = restTemplate.exchange("http://localhost:8080/inventory-service/update/" + updateBookRequest.getBookId(),
                        HttpMethod.POST, requestEntity,
                        APIResponseWithInventory.class).getBody();
                if (response.getData() == null) {
                    throw new BookServiceException("from inventory " + response.getError_message());
                }
                BookModel model = BookModel.builder()
                        .bookId(updateBookRequest.getBookId())
                        .bookName(updateBookRequest.getBookName())
                        .authorName(updateBookRequest.getAuthorName())
                        .genre(updateBookRequest.getGenre())
                        .price(updateBookRequest.getPrice())
                        .quantity(updateBookRequest.getQuantity())
                        .build();
                APIResponse<BookModel> apiResponse = new APIResponse<>(model, null);
                // Return the ResponseEntity with the APIResponse
                return ResponseEntity.ok(apiResponse);
                // return new ResponseEntity<>(updatedBook, HttpStatus.OK);
            } else {
                throw new BookServiceException("Book not found");
            }
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                throw new BookServiceException("Inventory Service is not available");
            }
            throw new BookServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> getAllBooks() {
        try {
            List<BookEntity> books = bookRepository.findAll();
            if (books.isEmpty()) {
                throw new BookServiceException("There is no book available in the stock");
            }
            List<Long> bookIds = books.stream().map(BookEntity::getBook_id).toList();
            headers.set("ALLOWED", INTERNAL_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List<Long>> requestEntity = new HttpEntity<>(bookIds, headers);
            APIResponseWithInventoryList listAPIResponse = restTemplate.exchange(
                    "http://localhost:8080/inventory-service/", HttpMethod.POST,
                    requestEntity, APIResponseWithInventoryList.class).getBody();
            if (listAPIResponse.getData() == null) {
                throw new BookServiceException(listAPIResponse.getError_message());
            }
            List<InventoryModel> inventoryModelList = listAPIResponse.getData();
            List<BookModel> modelList = new ArrayList<>();
            books.forEach(bookEntity -> {
                modelList.add(
                        BookModel.builder()
                                .bookId(bookEntity.getBook_id())
                                .bookName(bookEntity.getBookName())
                                .authorName(bookEntity.getAuthorName())
                                .genre(bookEntity.getGenre())
                                .price(inventoryModelList.stream().filter(i -> i.getBookId().equals(bookEntity.getBook_id())).findAny().get().getBookPrice())
                                .quantity(inventoryModelList.stream().filter(i -> i.getBookId().equals(bookEntity.getBook_id())).findAny().get().getBookQuantity())
                                .build()
                );
            });
            APIResponse<List<BookModel>> apiResponse = new APIResponse<List<BookModel>>(modelList, null);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                throw new BookServiceException("Inventory Service is not available");
            }
            throw new BookServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> deleteBookById(Long bookId) {
        try {
            if (bookRepository.existsById(bookId)) {
                bookRepository.deleteById(bookId);
                String message = "Book is deleted successfully";
                headers.set("ALLOWED", INTERNAL_KEY);
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity requestEntity = new HttpEntity<>(headers);
                APIResponse response = restTemplate.exchange(
                        "http://localhost:8080/inventory-service/delete/" + bookId, HttpMethod.DELETE,
                        requestEntity, APIResponse.class
                ).getBody();
                if (response.getData() == null) {
                    throw new BookServiceException(response.getError_message());
                }
                // Return the ResponseEntity with the APIResponse
                return ResponseEntity.ok(response);
            } else {
                throw new BookServiceException("Book not found");
            }
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                throw new BookServiceException("Inventory Service is not available");
            }
            throw new BookServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> getBookById(Long bookId) {
        try {
            Optional<BookEntity> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isPresent()) {
                headers.set("ALLOWED", INTERNAL_KEY);
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity requestEntity = new HttpEntity<>(headers);
                APIResponseWithInventory response = restTemplate.exchange(
                        "http://localhost:8080/inventory-service/" + bookId, HttpMethod.GET, requestEntity, APIResponseWithInventory.class).getBody();
                if (response.getData() == null) {
                    throw new BookServiceException("from inventory " + response.getError_message());
                }
                BookModel bookModel = BookModel.builder()
                        .bookId(bookId)
                        .bookName(optionalBook.get().getBookName())
                        .authorName(optionalBook.get().getAuthorName())
                        .genre(optionalBook.get().getGenre())
                        .price(response.getData().getBookPrice())
                        .quantity(response.getData().getBookQuantity())
                        .build();
                APIResponse<BookModel> apiResponse = new APIResponse<>(bookModel, null);
                // Return the ResponseEntity with the APIResponse
                return ResponseEntity.ok(apiResponse);
                //return ResponseEntity.ok(optionalBook.get());
            } else {
                throw new BookServiceException("Book not found");
            }
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                throw new BookServiceException("Inventory Service is not available");
            }
            throw new BookServiceException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> buyBook(BuyBookRequest buyBookRequest) {
        Long bookId = buyBookRequest.getId();
        Integer quantity = buyBookRequest.getQuantity();
        Span inventoryLookup = tracer.nextSpan().name("Buy Book");
        try (Tracer.SpanInScope spanInScope = tracer.withSpanInScope(inventoryLookup.start())) {
            Optional<BookEntity> book = bookRepository.findById(bookId);
            if (book.isEmpty()) {
                throw new BookServiceException("NO book Exist with this id");
            }
            headers.set("ALLOWED", INTERNAL_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BuyBookRequest> requestEntity = new HttpEntity<>(buyBookRequest, headers);
            APIResponseWithInventory inventoryResponse = restTemplate.exchange("http://localhost:8080/inventory-service/deduct",
                    HttpMethod.POST,
                    requestEntity,
                    APIResponseWithInventory.class).getBody();
            if (inventoryResponse.getData() == null) {
                throw new BookServiceException("from inventory " + inventoryResponse.getError_message());
            }
            BuyBookResponse buyBookResponse = BuyBookResponse.builder()
                    .bookId(bookId)
                    .bookName(book.get().getBookName())
                    .quantity(quantity)
                    .totalPrice(quantity * inventoryResponse.getData().getBookPrice())
                    .build();
            APIResponse<BuyBookResponse> finalResponse = new APIResponse<>(buyBookResponse, null);
            return ResponseEntity.ok(finalResponse);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                throw new BookServiceException("Inventory Service is not available");
            }
            throw new BookServiceException(e.getMessage());
        } finally {
            inventoryLookup.finish();
        }
    }
}
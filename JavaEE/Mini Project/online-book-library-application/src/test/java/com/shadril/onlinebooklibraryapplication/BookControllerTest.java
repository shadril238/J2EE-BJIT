package com.shadril.onlinebooklibraryapplication;

import com.shadril.onlinebooklibraryapplication.controller.BookController;
import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.exception.BookAlreadyExistsException;
import com.shadril.onlinebooklibraryapplication.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() throws BookAlreadyExistsException {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setStatus("AVAILABLE");
        book.setDescription("Test Description");
        book.setIsActive(true);
        book.setImgUrl("Test Image URL");

        Mockito.doNothing().when(bookService).create(book);

        ResponseEntity<String> response = bookController.createBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }
}

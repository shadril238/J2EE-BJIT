package com.shadril.onlinebooklibraryapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadril.onlinebooklibraryapplication.controller.BookController;
import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.repository.BookRepository;
import com.shadril.onlinebooklibraryapplication.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class BookControllerTest {
    private MockMvc mockMvc;
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }
    @Test
    public void createBookTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setDescription("Test Description");
        book.setAuthor("Test Author");
        book.setPageLength("100");
        book.setIsActive(true);
        book.setStatus("AVAILABLE");
        book.setImgUrl("Test Image URL");
        book.setLanguage("Test Language");


        doNothing().when(bookService).create(book);

//        mockMvc.perform(MockMvcRequestBuilders.post("/books/create")
//                        .content(book.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().string(containsString("Success")));

        String response = String.valueOf(bookController.createBook(book));

        assertEquals("Success", response);
    }
}

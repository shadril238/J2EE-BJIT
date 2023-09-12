package com.shadril.onlinebooklibraryapplication.dto;

import com.shadril.onlinebooklibraryapplication.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowBookInfoDTO {

    private Long borrowId;
    private Book bookEntity;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;


}


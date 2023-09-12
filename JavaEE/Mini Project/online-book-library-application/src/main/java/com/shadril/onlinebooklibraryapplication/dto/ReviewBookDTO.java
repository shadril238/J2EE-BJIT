package com.shadril.onlinebooklibraryapplication.dto;


import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewBookDTO {
    private Long id;
    private Long bookId;
    private Long userId;
    private Long rating;
    private String review;
    private LocalDate date;
}

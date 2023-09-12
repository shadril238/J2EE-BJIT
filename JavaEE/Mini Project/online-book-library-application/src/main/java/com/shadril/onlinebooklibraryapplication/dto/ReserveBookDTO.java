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
public class ReserveBookDTO {
    private Long reserveId;
    private Long userId;
    private Long bookId;
    private String status;
    private LocalDate reserveDate;
}

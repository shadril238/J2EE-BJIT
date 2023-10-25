package com.shadril.onlinebooklibraryapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "reviews")
public class ReviewBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review-books")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private String review;

    private LocalDate date;
}

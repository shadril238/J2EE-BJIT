package com.shadril.onlinebooklibraryapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private String pageLength;
    private String language;
    private String author;
    private String status;
    private Boolean isActive;
}

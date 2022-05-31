package com.app.library.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private LocalDate releaseDate;

}

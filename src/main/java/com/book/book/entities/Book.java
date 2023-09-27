package com.book.book.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id // This annotation specifies that the 'id' field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean availability;

    @Column(nullable = false)
    private String ratings;
}

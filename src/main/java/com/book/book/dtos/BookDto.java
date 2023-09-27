package com.book.book.dtos;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class BookDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private boolean availability;
    private String ratings;
}

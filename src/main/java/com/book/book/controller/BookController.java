package com.book.book.controller;

import com.book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

      @Autowired
      private BookService bookService;
    @RequestMapping(value ="/scrape",method = RequestMethod.POST,  produces = "application/json")
    public ResponseEntity<String> scrapeBooks() {
        bookService.scrapeAndStoreBooks();
        return ResponseEntity.status(HttpStatus.OK).body("Scraping and storing books completed.");
    }
}
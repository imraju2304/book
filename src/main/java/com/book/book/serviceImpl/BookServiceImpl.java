package com.book.book.serviceImpl;

import com.book.book.entities.Book;
import com.book.book.repository.BookRepository;
import com.book.book.service.BookService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void scrapeAndStoreBooks() {
        // URL of the website to scrape
        String url = "http://books.toscrape.com";

        try {
            for (int page = 1; page <= 50; page++) {
                String pageUrl = url + "/catalogue/page-" + page + ".html";

                // Send an HTTP GET request to the page
                Document document = Jsoup.connect(pageUrl).get();

                // Extract book information
                Elements bookElements = document.select("article.product_pod");
                List<Book> books = new ArrayList<>();

                for (Element bookElement : bookElements) {
                    String title = bookElement.select("h3 a").text();
                    BigDecimal price = new BigDecimal(bookElement.select("div p.price_color").text().replaceAll("[^\\d.]", ""));
                    boolean availability = !bookElement.select("div p.availability").text().contains("Out of stock");
                    String ratings = bookElement.select("p.star-rating").attr("class").toLowerCase().replace("star-rating", "").trim();

                    // Create a Book entity and add it to the list
                    Book book = new Book();
                    book.setTitle(title);
                    book.setPrice(price);
                    book.setAvailability(availability);
                    book.setRatings(ratings);
                    books.add(book);
                }
                // Save the extracted books to the database
                bookRepository.saveAll(books);
            }
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}

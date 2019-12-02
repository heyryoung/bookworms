package kr.bookworm.bookwormapp.controller;

import kr.bookworm.bookwormapp.entity.Book;
import kr.bookworm.bookwormapp.entity.BookSearchCategory;
import kr.bookworm.bookwormapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/list")
    public List<Book> searchList(@RequestParam(required = false) String query, @RequestParam(required = false) BookSearchCategory bookSearchCategory) {
        return bookService.getSearchedBooks(query, bookSearchCategory.getValue());
    }
}

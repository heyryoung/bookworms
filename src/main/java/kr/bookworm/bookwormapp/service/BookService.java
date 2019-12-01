package kr.bookworm.bookwormapp.service;

import kr.bookworm.bookwormapp.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    List<Book> getSearchedBooks(String query, String category);
}

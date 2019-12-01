package kr.bookworm.bookwormapp.controller;

import kr.bookworm.bookwormapp.entity.Book;
import kr.bookworm.bookwormapp.entity.BookSearchCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class BookRestControllerTest {

    @Autowired
    BookRestController bookRestController;

    @Test
    public void sampleTest() throws Exception {
        List<Book> books = bookRestController.searchList("프리렉", BookSearchCategory.PUBLISHER);
        for (Book book : books) {
            log.info("book : {} ", book);
        }

        assertThat(books).isNotNull();
     }
}
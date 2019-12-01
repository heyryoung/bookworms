package kr.bookworm.bookwormapp.controller;

import kr.bookworm.bookwormapp.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class APIControllerTest {

    @Autowired
    APIController apiController;

    @Test
    public void sampleTest() throws Exception {
        List<Book> books = apiController.searchList("스프링");
        for (Book book : books) {
            log.info("book : {} ", book);
        }

        assertThat(books).isNotNull();
     }
}
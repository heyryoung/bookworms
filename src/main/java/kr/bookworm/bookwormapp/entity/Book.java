package kr.bookworm.bookwormapp.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String title;
    private String contents;
    private String url;
    private String isbn;
    private String datetime;
    private List<String> authors;
    private String publisher;
    private List<String> translators;
    private int price;
    private int sale_price;
    private String thumbnail;
    private String status;

}

package kr.bookworm.bookwormapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kr.bookworm.bookwormapp.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final RestTemplate restTemplate;

    private static final String AUTHORIZATION = "KakaoAK 594159c8cb7241e6fc93aeeef832c221";

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getSearchedBooks(String query, String category) {
        String url = "https://dapi.kakao.com/v3/search/book";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("target",category).build();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.AUTHORIZATION, AUTHORIZATION);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        final ResponseEntity<String> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        List<Object> objectArrayList;
        List<Book> books = new ArrayList<>();

        if(exchange.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            JacksonJsonParser jsonParser = new JacksonJsonParser();
            Gson gson = new Gson();

            Map<String, Object> stringObjectMap = jsonParser.parseMap(exchange.getBody());
            JsonElement documents = gson.toJsonTree(stringObjectMap.get("documents"));
            objectArrayList = jsonParser.parseList(documents.toString());
            for (Object o : objectArrayList) {
                final Book book = mapper.convertValue(o, Book.class);
                books.add(book);
            }
        }
        return books;
    }
}

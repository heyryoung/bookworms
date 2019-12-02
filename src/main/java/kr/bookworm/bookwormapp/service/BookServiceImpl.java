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

    private static final String URL = "https://dapi.kakao.com/v3/search/book";

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getSearchedBooks(String query, String category) {
        UriComponents builder = getUriComponents(query, category);
        HttpEntity<?> entity = getHttpEntity();

        final ResponseEntity<String> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        List<Book> books = new ArrayList<>();
        if(exchange.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            List<Object> bookObjects = getObjectArrayList(exchange);

            convertObjectToBook(books, mapper, bookObjects);
        }
        return books;
    }

        private void convertObjectToBook(List<Book> books, ObjectMapper mapper, List<Object> bookObjects) {
            for (Object o : bookObjects) {
                final Book book = mapper.convertValue(o, Book.class);
                books.add(book);
            }
        }

        private List<Object> getObjectArrayList(ResponseEntity<String> exchange) {
                JacksonJsonParser jsonParser = new JacksonJsonParser();
                Gson gson = new Gson();

                Map<String, Object> stringObjectMap = jsonParser.parseMap(exchange.getBody());
                JsonElement documents = gson.toJsonTree(stringObjectMap.get("documents"));
                return jsonParser.parseList(documents.toString());
        }

        private HttpEntity<?> getHttpEntity() {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.AUTHORIZATION, BookServiceImpl.AUTHORIZATION);
            return new HttpEntity<>(headers);
        }

        private UriComponents getUriComponents(String query, String category) {
            return UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("query", query)
                        .queryParam("target",category).build();
        }
}

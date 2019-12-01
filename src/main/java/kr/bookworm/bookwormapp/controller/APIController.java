package kr.bookworm.bookwormapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kr.bookworm.bookwormapp.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api")
public class APIController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String AUTHORIZATION = "KakaoAK 594159c8cb7241e6fc93aeeef832c221";

    @GetMapping("/list")
    public List<Book> searchList(@RequestParam String query) {
        String url = "https://dapi.kakao.com/v3/search/book?target=title&query="+query;


        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.AUTHORIZATION, AUTHORIZATION);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        final ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


        List<Object> objectArrayList = new ArrayList<>();
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

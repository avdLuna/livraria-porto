package com.livraria.livraria.books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class BookCrawler {
    private static final String API_KEY = "AIzaSyBVVMFGlNDYTCkCWUVZ3vYyj0dpjJF1k8s";
    private static final String BOOK_API_LINK = "https://www.googleapis.com/books/v1/volumes?q=";

    private final RestTemplate restTemplate;

    public BookCrawler(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public List<Book> getBooksByName(String name) throws JsonProcessingException {
        String url = BOOK_API_LINK + name + "&key=" + API_KEY;
        String result = this.restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        Map<String, Object> jsonMap = mapper.readValue(result, new TypeReference<Map<String,Object>>(){});

        return (List<Book>) jsonMap.get("items");
    }
    public List<Book> getBooksByAuthor(String author){
        String url = BOOK_API_LINK + "inauthor:" + author + "&key=" + API_KEY;
        return this.restTemplate.getForObject(url, List.class);
    }

    public List<Book> getBooksByNameAndAuthor(String name, String author){
        String url = BOOK_API_LINK + name + "&inauthor:" + author + "&key=" + API_KEY;
        return this.restTemplate.getForObject(url, List.class);
    }

}

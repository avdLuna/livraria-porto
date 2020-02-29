package com.livraria.livraria.books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.livraria.livraria.util.Validator;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class BookService {
    private BookCrawler crawler = new BookCrawler(new RestTemplateBuilder());
    private Validator validator = new Validator();

    public List<Book> searchByName(String name) throws ValidatorException, JsonProcessingException {
        if(validator.validString(name)){
            return this.crawler.getBooksByName(name);
        } else {
            throw new ValidatorException("Invalid name");
        }
    }

    public List<Book> searchByAuthor(String author) throws ValidatorException {
        if(validator.validString(author)){
            return this.crawler.getBooksByAuthor(author);
        } else {
            throw new ValidatorException("Invalid author");
        }
    }

    public List<Book> searchByNameAndAuthor(String name, String author) throws ValidatorException {
        if(validator.validString(name) && validator.validString(author)){
            return this.crawler.getBooksByNameAndAuthor(name, author);
        } else {
            throw new ValidatorException("Invalid name or author");
        }
    }

    public Book searchById(String id) throws JsonProcessingException, UnsupportedEncodingException {
        String infoBook = this.crawler.getBooksById(id);
        JsonParser parser = new JsonParser();
        JsonElement volumeInfo = parser.parse(infoBook).getAsJsonObject()
                            .get("items").getAsJsonArray()
                            .get(0).getAsJsonObject()
                            .get("volumeInfo");
        System.out.println(volumeInfo.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = objectMapper.readValue(volumeInfo.toString(),Book.class);
        book.setId(id);
        System.out.println(book.toString());

        return book;
    }

}

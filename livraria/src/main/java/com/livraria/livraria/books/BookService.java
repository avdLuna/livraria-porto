package com.livraria.livraria.books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.livraria.livraria.util.Validator;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Book searchById(String id) throws JsonProcessingException {
        String infoBook = this.crawler.getBooksById(id);
        List<String> infoJson = retrievesBookString(infoBook);
        return convertJsonToBook(infoJson);
    }

    public Book convertJsonToBook(List<String> json) {
        Gson gson = new Gson();
        Book book = gson.fromJson(json.get(0), Book.class);
        return book;
    }

    public List<String> retrievesBookString(String infos) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        Map<String, Object> jsonMap = mapper.readValue(infos, new TypeReference<Map<String,Object>>(){});

        return (List<String>) jsonMap.get("items");
    }


}

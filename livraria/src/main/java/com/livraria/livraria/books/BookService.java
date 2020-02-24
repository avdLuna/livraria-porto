package com.livraria.livraria.books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livraria.livraria.util.Validator;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

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
}

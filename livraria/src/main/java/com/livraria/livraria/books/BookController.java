package com.livraria.livraria.books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livraria.livraria.customer.Customer;
import com.livraria.livraria.customer.CustomerService;
import com.livraria.livraria.jwt.JwtService;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> getCustomer(@RequestHeader("Authorization") String header, @RequestBody Book book) throws ValidatorException, ServletException, JsonProcessingException {
        String email = jwtService.recoverSubjectFromToken(header);
        if(!customerService.customerEmailExists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Book> books;
            if(!book.getTitle().isEmpty() && book.getAuthors() != null){
               books = bookService.searchByNameAndAuthor(book.getTitle(), book.getAuthors().get(0));
            } else if(book.getTitle().isEmpty()){
                books = bookService.searchByAuthor(book.getAuthors().get(0));
            } else {
                books = bookService.searchByName(book.getTitle());
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

}

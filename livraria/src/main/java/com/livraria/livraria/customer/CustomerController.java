package com.livraria.livraria.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livraria.livraria.books.Book;
import com.livraria.livraria.books.BookService;
import com.livraria.livraria.jwt.JwtService;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private BookService bookService;

    @PostMapping("/customer/create")
    public ResponseEntity<Customer> createUser(@Valid @RequestBody Customer customer)  throws ValidatorException {
        System.out.println("CREATE USER " + customer.getName() + "...");
        Customer customerCreated = customerService.create(customer);
        if (customerCreated != null){
            return new ResponseEntity<>(customerCreated, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@RequestHeader("Authorization") String header, @PathVariable("id") String id) throws ValidatorException, ServletException {
        String email = jwtService.recoverSubjectFromToken(header);
        if(!customerService.customerEmailExists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Customer customer =  customerService.getCustomerById(id).get();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestHeader("Authorization") String header, @PathVariable("id") String id, @RequestBody Customer customer) throws ValidatorException, ServletException {
        String email = jwtService.recoverSubjectFromToken(header);
        Customer customerAux = customerService.getCustomerByEmail(email);
        if(!customerService.customerEmailExists(email) && !customerAux.getEmail().equals(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Customer customerRes =  customerService.update(customerAux.getId(), customer);
            return new ResponseEntity<>(customerRes, HttpStatus.OK);
        }
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader("Authorization") String header) throws ValidatorException, ServletException {
        String email = jwtService.recoverSubjectFromToken(header);
        if(!customerService.customerEmailExists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Customer> customers =  customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    @PostMapping("/customer/addBook")
    public ResponseEntity<Book> addBookToCollection(@RequestHeader("Authorization") String header, @RequestBody Book book) throws ServletException, JsonProcessingException, ValidatorException, UnsupportedEncodingException {
        String email = jwtService.recoverSubjectFromToken(header);
        Book bSearch = bookService.searchById(book.getId());
        return new ResponseEntity<>(customerService.addBookCollection(email, bSearch), HttpStatus.CREATED);
    }

}

package com.livraria.livraria.customer;

import com.livraria.livraria.jwt.JwtService;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtService jwtService;

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
        if(!customerService.userEmailExists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Customer customer =  customerService.getCustomerById(id).get();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader("Authorization") String header) throws ValidatorException, ServletException {
        String email = jwtService.recoverSubjectFromToken(header);
        if(!customerService.userEmailExists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Customer> customers =  customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

}

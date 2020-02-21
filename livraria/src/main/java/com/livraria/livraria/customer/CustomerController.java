package com.livraria.livraria.customer;

import com.livraria.livraria.params.GeneralParams;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> createUser(@RequestBody GeneralParams generalParams)  throws ValidatorException {
        System.out.println("CREATE USER " + generalParams.getCustomer().getName() + "...");
        Customer customerCreated = customerService.create(generalParams.getCustomer());
        if (customerCreated != null){
            return new ResponseEntity<Customer>(customerCreated, HttpStatus.OK);
        } else{
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

}

package com.livraria.livraria.address;

import com.livraria.livraria.customer.CustomerService;
import com.livraria.livraria.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/address/add")
    public ResponseEntity<Address> addAddress(@RequestHeader("Authorization") String header, @RequestBody Address address) throws ServletException {

        String email = jwtService.recoverSubjectFromToken(header);

        if(!customerService.customerEmailExists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Address>(this.addressService.addAddress(address),HttpStatus.CREATED);
        }


    }


}

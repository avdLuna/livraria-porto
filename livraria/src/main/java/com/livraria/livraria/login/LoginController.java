package com.livraria.livraria.login;

import com.livraria.livraria.customer.Customer;
import com.livraria.livraria.customer.CustomerService;
import com.livraria.livraria.jwt.JwtService;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
public class LoginController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtService jwtService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/auth/login")
    public LoginResponse auth(@RequestBody Customer customer){
        try {
            Customer customerAux = this.customerService.getCustomerByEmail(customer.getEmail());
            if(!this.bCryptPasswordEncoder.matches(customerAux.getPassword(), customer.getPassword()))
                throw new ServletException("Invalid password");
        } catch (ValidatorException | ServletException e) {
            e.printStackTrace();
        }

        String generatedToken = this.jwtService.generateToken(customer.getEmail());
        LoginResponse loginResponse =  new LoginResponse(generatedToken);
        System.out.println(loginResponse.toString());
        return loginResponse;
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "LoginResponse{" +
                    "token='" + token + '\'' +
                    '}';
        }
    }


}

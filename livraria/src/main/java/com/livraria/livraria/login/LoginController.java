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
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping("/auth/login")
    public LoginResponse auth(@RequestBody Customer customer){
        try {
            Customer customerAux = this.customerService.getCustomerByEmail(customer.getEmail());
            if(!bCryptPasswordEncoder.matches(customer.getPassword(), customerAux.getPassword())){
                throw new ServletException("Invalid password");
            }
            customerAux.setPassword(null);
            customerAux.setBooks(null);
            customerAux.setAdresses(null);
            String generatedToken = this.jwtService.generateToken(customer.getEmail(), customerAux);
            LoginResponse loginResponse =  new LoginResponse(generatedToken);
            return loginResponse;
        } catch (ValidatorException | ServletException e) {
            e.printStackTrace();
        }
        return new LoginResponse(null);
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

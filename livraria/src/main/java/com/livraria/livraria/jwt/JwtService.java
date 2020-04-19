package com.livraria.livraria.jwt;

import com.livraria.livraria.customer.Customer;
import com.livraria.livraria.customer.CustomerService;
import com.livraria.livraria.util.ValidatorException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private CustomerService customerService;
    private final String TOKEN_SECRET = "passsecret";

    public JwtService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String generateToken(String email, Object customer){
        String token = Jwts.builder()
                            .setSubject(email)
                            .claim("user", customer)
                            .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                            .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                            .compact();
        return token;
    }

    public boolean customerExists(String authorizationHeader) throws ServletException, ValidatorException {
        String email = recoverSubjectFromToken(authorizationHeader);
        return customerService.customerEmailExists(email);
    }

    public boolean customerHasPermission(String authorizationHeader, String email) throws ServletException, ValidatorException {
        String subject = recoverSubjectFromToken(authorizationHeader);
        Customer customer = customerService.getCustomerByEmail(subject);
        return customer != null && customer.getEmail().equals(email);
    }

    public String recoverSubjectFromToken(String authorizationHeader) throws ServletException {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or badly formatted token");
        }

        String token = authorizationHeader.substring(JwtFilter.TOKEN_INDEX);

        String subject = null;

        try {
            subject = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Invalid or expired token");
        }
        return subject;
    }
}

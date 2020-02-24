package com.livraria.livraria.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Non existent or badly formatted Token ");
        }

        String token = header.substring(TOKEN_INDEX);

        try {
            Jwts.parser().setSigningKey("passsecret").parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}

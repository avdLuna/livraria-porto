package com.livraria.livraria;

import com.livraria.livraria.jwt.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LivrariaApplication {
	@Bean
	public FilterRegistrationBean<JwtFilter> filtroJwt() {

		FilterRegistrationBean<JwtFilter> filterRB = new FilterRegistrationBean<JwtFilter>();

		filterRB.setFilter(new JwtFilter());
		filterRB.addUrlPatterns("/customer/get");
		return filterRB;

	}
	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

}

package com.livraria.livraria.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    public List<Customer> findByName(String name);
    public Optional<Customer> findById(String id);
    public Customer findByEmail(String email);
}

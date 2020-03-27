package com.livraria.livraria.books;

import com.livraria.livraria.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    public Optional<Book> findById(String id);

}

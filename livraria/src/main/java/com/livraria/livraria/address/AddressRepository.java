package com.livraria.livraria.address;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AddressRepository extends MongoRepository<Address, String>  {

    public Optional<Address> findById(String id);

}

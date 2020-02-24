package com.livraria.livraria.customer;

import com.livraria.livraria.util.Validator;
import com.livraria.livraria.util.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private Validator validator = new Validator();

    public Customer create(Customer customer) throws ValidatorException {
        Customer customerAux = this.validCreate(customer);
        if(customerAux != null ){
           return customerRepository.save(customerAux);
        }
        return customerAux;
    }

    public boolean userEmailExists(String email) {
        Iterable<Customer> customersIterator = customerRepository.findAll();
        for (Customer customer : customersIterator) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomerByEmail(String email) throws ValidatorException {
        if(validator.validEmail(email)) {
            return customerRepository.findByEmail(email);
        } else {
            throw new ValidatorException("Emails doesn`t exists");
        }
    }

    public List<Customer> getAllCustomers() throws ValidatorException {
       List<Customer> customers = customerRepository.findAll();
       if(customers.isEmpty()){
           throw new NullPointerException("No registered users");
       }
       return customers;
    }

    public Optional<Customer> getCustomerById(String id) throws ValidatorException {
        if(validator.validString(id)){
            return customerRepository.findById(id);
        } else {
            throw new ValidatorException("Customer does`t exists");
        }
    }

    private Customer validCreate(Customer customer)  throws ValidatorException {
        if (validator.validString(customer.getName()) && validator.validEmail(customer.getEmail())
                && validator.validPassword(customer.getPassword())) {
            if (!this.userEmailExists(customer.getEmail())) {
                customer.setName(customer.getName());
                customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
                customer.setEmail(customer.getEmail());
                return customer;
            } else {
                throw new ValidatorException("Email alread exists");
            }
        } else {
            return null;
        }

    }

}

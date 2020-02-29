package com.livraria.livraria.customer;

import com.livraria.livraria.books.Book;
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

    public boolean customerEmailExists(String email) {
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

    public Customer update(String id, Customer customer)  throws ValidatorException {
        Optional<Customer> customerData = customerRepository.findById(id);
        if (customerData.isPresent()) {
            Customer customerUser = validUpdate(customerData.get(), customer);
            if (customerUser != null) {
                return customerRepository.save(customerUser);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    private Customer validCreate(Customer customer)  throws ValidatorException {
        if (validator.validString(customer.getName()) && validator.validEmail(customer.getEmail())
                && validator.validPassword(customer.getPassword())) {
            if (!this.customerEmailExists(customer.getEmail())) {
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

    private Customer validUpdate(Customer customer, Customer customerUpdate)  throws ValidatorException{
            if(customerUpdate.getName() != null) {
                customer.setName(customerUpdate.getName());
            }
            if(customerUpdate.getPassword() != null) {
                customer.setPassword(customerUpdate.getPassword());
            }
            if(customerUpdate.getRole() != null) {
                customer.setRole(customerUpdate.getRole());
            }
            if(customerUpdate.getEmail() != null) {
                customer.setEmail(customerUpdate.getEmail());
            }
            return customer;
    }

    public Book addBookCollection(String email, Book book) throws ValidatorException {
        Customer customer = getCustomerByEmail(email);
        customer.getBooks().add(book);
        customerRepository.save(customer);
        return book;
    }
}

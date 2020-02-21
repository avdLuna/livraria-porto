package com.livraria.livraria.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livraria.livraria.customer.Customer;

public class GeneralParams {
    private Customer customer;

    @JsonProperty("customer")
    public void setCustomerParams(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

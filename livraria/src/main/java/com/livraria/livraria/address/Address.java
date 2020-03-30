package com.livraria.livraria.address;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Address {

    @Id
    private String id;

    private String city;
    private String state;
    private String streetName;
    private float number;
    private String complement;

    public Address(String id, String city, String state, String streetName, float number, String complement) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
    }

    public Address() {
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreetName() {
        return streetName;
    }

    public float getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", streetName='" + streetName + '\'' +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Float.compare(address.number, number) == 0 &&
                Objects.equals(id, address.id) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(streetName, address.streetName) &&
                Objects.equals(complement, address.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

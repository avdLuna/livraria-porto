package com.livraria.livraria.books;

public class IndustryIdentifiers {
    private String type;
    private String identifier;

    public IndustryIdentifiers() {
    }

    public IndustryIdentifiers(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "IndustryIdentifiers{" +
                "type='" + type + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}

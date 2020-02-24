package com.livraria.livraria.books;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "books")
public class Book {
    private String id;
    private String title;
    private String subtitle;
    private String description;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private List<IndustryIdentifiers> industryIdentifiers;
    private int pageCount;
    private String language;
    private List<String> imageLinks;
    private float price;

    public Book() {
    }

    public Book(String id,
                String title,
                String subtitle,
                String description,
                List<String> authors,
                String publisher,
                String publishedDate,
                List<IndustryIdentifiers> industryIdentifiers,
                int pageCount,
                String language,
                List<String> imageLinks) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.industryIdentifiers = industryIdentifiers;
        this.pageCount = pageCount;
        this.language = language;
        this.imageLinks = imageLinks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<IndustryIdentifiers> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifiers> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", industryIdentifiers=" + industryIdentifiers +
                ", pageCount=" + pageCount +
                ", language='" + language + '\'' +
                ", imageLinks=" + imageLinks +
                ", price=" + price +
                '}';
    }
}

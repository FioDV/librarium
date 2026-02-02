package com.example.librarium.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String language;
    private int downloads;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    public Book(){};

    public Book(BookData bookData, Author author) {
        this.title = bookData.title();
        this.author = author;
        this.language = bookData.language().get(0);
        this.downloads = bookData.downloads();
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", languages='" + language + '\'' +
                ", downloads=" + downloads;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }
}

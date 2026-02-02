package com.example.librarium.repository;

import com.example.librarium.model.Author;
import com.example.librarium.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> searchByTitleContainsIgnoreCase (String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.language = :language")
    List<Book> searchByLanguage (String language);

}

package com.example.librarium;

import com.example.librarium.main.Menu;
import com.example.librarium.repository.AuthorRepository;
import com.example.librarium.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrariumApplication implements CommandLineRunner {

    @Autowired
    private BookRepository repository;
    @Autowired
    private AuthorRepository authorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LibrariumApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Choose one of the options:");
        Menu menu = new Menu(repository, authorRepository);
        menu.displayMenu();



    }

}

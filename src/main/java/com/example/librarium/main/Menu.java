package com.example.librarium.main;

import com.example.librarium.model.*;
import com.example.librarium.repository.AuthorRepository;
import com.example.librarium.repository.BookRepository;
import com.example.librarium.service.APIClient;
import com.example.librarium.service.DataConverter;


import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private APIClient apiClient = new APIClient();
    private final String URL = "https://gutendex.com/books/";
    private DataConverter converter = new DataConverter();
    private BookRepository repository;
    private AuthorRepository authorRepository;
    private Optional<Book> bookSearched;
    private List<Book> books;
    private List<Book> authors;
    List<Author> authorAlive;
    List<Book> bookByLanguage;

    public Menu(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public void displayMenu(){
        var option = -1;
        while (option != 0){
            var menu = """
               **********************************************
               1. Find a book.
               2. Search book in Database by title.
               3. List of consulted books.
               4. List of consulted authors.
               5. List of authors alive in a specific year.
               6. Search books by language.
               0. Exit
               **********************************************
               """;
            System.out.println(menu);
            try{
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option){
                    case 1:
                        findBook();
                        break;
                    case 2:
                        searchByTitle();
                        break;
                    case 3:
                        consultedBooks();
                        break;
                    case 4:
                        consultedAuthors();
                        break;
                    case 5:
                        authorsAliveInYear();
                        break;
                    case 6:
                        searchBookByLanguage();
                        break;
                    case 0:
                        System.out.println("Exiting the application.");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Insert valid option.");
                scanner.nextLine();
            }

        }
    }

    private BookData getBookData(){
        while (true) {
            System.out.println("Insert the book you want to search:");
            var bookTitle = scanner.nextLine();
            var json = apiClient.getData(URL + "?search=" + bookTitle.replace(" ", "%20"));
            ApiResponse response = converter.getData(json, ApiResponse.class);

            if (response.results() != null && !response.results().isEmpty()) {
                return response.results().get(0);
            } else {
                System.out.println("No books found with that title. Please try again.");
            }
        }

    }

    private void findBook(){
        BookData data = getBookData();
        AuthorData authorData = data.author().get(0);
        Author author = new Author(authorData);
        Book book = new Book(data, author);
        repository.save(book);
        System.out.println(data);
    }

    private void searchByTitle(){
        System.out.println("Insert the title of the book you want to search:");
        var bookTitle = scanner.nextLine();
        bookSearched = repository.searchByTitleContainsIgnoreCase(bookTitle);
        if(bookSearched.isPresent()){
            System.out.println("The book found is: " + bookSearched.get());
        } else {
            System.out.println("No book was found.");
        }
    }

    private void consultedBooks(){
        System.out.println("The books you searched are:");
        books = repository.findAll();
        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    private void consultedAuthors(){
        System.out.println("The authors you have searched are:");
        authors = repository.findAll();
        authors.stream()
                .map(Book::getAuthor)
                .forEach(System.out::println);
    };

    private void authorsAliveInYear(){
        System.out.println("Insert the year you want to search:");
        var year = scanner.nextInt();
        authorAlive = authorRepository.findAuthorsAliveInYear(year);
        if(!authorAlive.isEmpty()){
            System.out.println("Authors found: ");
            authorAlive.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(System.out::println);
        } else {
            System.out.println("No author was found.");
        }
    };

    private void searchBookByLanguage(){
        System.out.println("""
                - Type "en" for English
                - Type "es" for Spanish""");
        var language = scanner.nextLine();
        Map<String, String> languageMap = Map.of(
                "en", "English",
                "es", "Spanish"
        );

        String languageName = languageMap.getOrDefault(language.toLowerCase(), language);

        bookByLanguage = repository.searchByLanguage(language);
        System.out.println("The books in " + languageName + " language are:");
        bookByLanguage.stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    };
}

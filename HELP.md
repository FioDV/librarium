# Librarium

## Description
This project is an application that fetch data from a book API (https://gutendex.com/), and use filters to search books by title, author and more. It was built using Java and Spring Boot with the purpose of put in practice what I have learn.

Note: This API doesn't need API KEY.

## Dependencies
These are the dependencies that where installed.
- Jackson
- Spring Data JPA
- PostgreSQL Driver

## DataBase Settings
The database was created in PostgreSQL (https://www.postgresql.org/download/), to connect your project to the Database insert this code into the application.properties file on your Java project. Replace the placeholders with your own database data.


spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql = true


# Thecnology / Stack

- Java 17 (por ejemplo)
- Spring Boot 3
- PostgreSQL
- Maven
- Jackson
- JPA / Hibernate

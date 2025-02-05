package com.example.bookstore.config

import com.example.bookstore.domain.Book
import com.example.bookstore.domain.BookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseInitializer {

    @Bean
    fun initDatabase(bookRepository: BookRepository) = CommandLineRunner {
        if (bookRepository.count() == 0L) {  // Prevent duplicates on restart
            var book1 = Book(id = null, title = "The Hobbit", author = "J.R.R. Tolkien", publicationYear = 1937, isbn = "1234567890", price = 15.99)
            var book2 = Book(id = null, title = "1984", author = "George Orwell", publicationYear = 1949, isbn = "9876543210", price = 12.99)
            var book3 = Book(id = null, title = "Clean Code", author = "Robert C. Martin", publicationYear = 2008, isbn = "1593279507", price = 29.99)

            bookRepository.saveAll(listOf(book1, book2, book3))
            println("Sample books added to the database!")
        }
    }
}

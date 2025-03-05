// filepath: /Users/ben/Documents/GitHub/BackendSpring25Kotlin/bookstore/src/main/kotlin/com/example/bookstore/config/DatabaseInitializer.kt
package com.example.bookstore.config

import com.example.bookstore.domain.Book
import com.example.bookstore.domain.BookRepository
import com.example.bookstore.domain.Category
import com.example.bookstore.domain.CategoryRepository
import com.example.bookstore.domain.User
import com.example.bookstore.domain.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class DatabaseInitializer {

    @Bean
    fun initDatabase(bookRepository: BookRepository,
                    categoryRepository: CategoryRepository,
                    userRepository: UserRepository,
                    passwordEncoder: PasswordEncoder) = CommandLineRunner {
        if (categoryRepository.count() == 0L) {
            val category1 = Category(categoryId = null, name = "Fiction")
            val category2 = Category(categoryId = null, name = "Non-Fiction")
            categoryRepository.saveAll(listOf(category1, category2))
            println("Sample categories added to the database!")
        }

        if (bookRepository.count() == 0L) {
            val category1 = categoryRepository.findById(1L).get()
            val category2 = categoryRepository.findById(2L).get()

            val book1 = Book(bookId = null, title = "The Hobbit", author = "J.R.R. Tolkien", publicationYear = 1937, isbn = "1234567890", price = 15.99, category = category1)
            val book2 = Book(bookId = null, title = "1984", author = "George Orwell", publicationYear = 1949, isbn = "9876543210", price = 12.99, category = category2)
            val book3 = Book(bookId = null, title = "Clean Code", author = "Robert C. Martin", publicationYear = 2008, isbn = "1593279507", price = 29.99, category = category2)

            bookRepository.saveAll(listOf(book1, book2, book3))
            println("Sample books added to the database!")
        }
        if (userRepository.count() == 0L) {
            val user = User(
                username = "user",
                passwordHash = passwordEncoder.encode("password"),
                email = "user@example.com",
                role = "USER"
            )
            val admin = User(
                username = "admin",
                passwordHash = passwordEncoder.encode("password"),
                email = "admin@example.com",
                role = "ADMIN"
            )
            userRepository.save(user)
            userRepository.save(admin)
            println("Sample users added to the database!")
        }
    }
}
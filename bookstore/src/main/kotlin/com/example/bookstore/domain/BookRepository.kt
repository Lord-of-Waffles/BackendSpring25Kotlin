package com.example.bookstore.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.example.bookstore.domain.Book

interface BookRepository : CrudRepository<Book, Long> {
    fun findByTitle(title: String): List<Book>
    fun findByAuthor(author: String): List<Book>
    fun findByPublicationYear(year: Int): List<Book>
    fun findByIsbn(isbn: String): Book?
    fun findByPrice(price: Double): List<Book>
}
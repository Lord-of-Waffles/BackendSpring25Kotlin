package com.example.bookstore.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import com.example.bookstore.domain.Book
import com.example.bookstore.domain.BookRepository

@Controller
class BookRestController @Autowired constructor(private val bookRepository: BookRepository) {

    @RequestMapping("/books", method = [RequestMethod.GET])
    @ResponseBody
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    @RequestMapping("/books/{id}", method = [RequestMethod.GET])
    @ResponseBody
    fun getBook(@PathVariable id: Long): Book {
        return bookRepository.findById(id).orElseThrow { RuntimeException("Book not found") }
    }

}
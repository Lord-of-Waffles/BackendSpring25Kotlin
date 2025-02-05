package com.example.bookstore.web

import com.example.bookstore.domain.Book
import com.example.bookstore.domain.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BookController {

    @Autowired
    private lateinit var repository: BookRepository

    @GetMapping("/index")
    fun index(model: Model): String {
        val books = listOf(
            Book(id = null, title = "A Farewell to Arms", author = "Ernest Hemingway", publicationYear = 1929, isbn = "1232323-21", price = 16.99),
            Book(id = null, title = "Animal Farm", author = "George Orwell", publicationYear = 1945, isbn = "2212343-5", price = 14.99)
        )
        model.addAttribute("books", books)
        return "index"
    }

    @GetMapping("/booklist")
    fun booklist(model: Model): String {
        model.addAttribute("books", repository.findAll())
        return "booklist"
    }
}
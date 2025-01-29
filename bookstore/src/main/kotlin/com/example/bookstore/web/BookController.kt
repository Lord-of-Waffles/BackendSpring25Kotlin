package com.example.bookstore.web

import com.example.bookstore.domain.Book
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BookController {

    @GetMapping("/index")
    fun index(model: Model): String {
        val books = listOf(
            Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 16.99),
            Book("Animal Farm", "George Orwell", 1945, "2212343-5", 14.99),
        )
        model.addAttribute("books", books)
        return "index"
    }
}
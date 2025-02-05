package com.example.bookstore.web

import com.example.bookstore.domain.Book
import com.example.bookstore.domain.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable

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

    @RequestMapping("/add")
    fun addBook(model: Model): String {
        model.addAttribute("book", Book())
        return "addbook"
    }

    @RequestMapping("/delete/{id}")
    fun deleteBook(@PathVariable id: Long, model: Model): String {
        repository.deleteById(id)
        return "redirect:/booklist"
    }

    @PostMapping("/save")
    fun save(book: Book): String {
        repository.save(book)
        return "redirect:booklist"
    }

    @GetMapping("/booklist")
    fun booklist(model: Model): String {
        model.addAttribute("books", repository.findAll())
        return "booklist"
    }
}
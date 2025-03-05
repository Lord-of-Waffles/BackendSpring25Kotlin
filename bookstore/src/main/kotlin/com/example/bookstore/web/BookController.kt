package com.example.bookstore.web

import com.example.bookstore.domain.Book
import com.example.bookstore.domain.BookRepository
import com.example.bookstore.domain.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BookController {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @GetMapping("/index")
    fun index(model: Model): String {
        val books = listOf(
            Book(bookId = null, title = "A Farewell to Arms", author = "Ernest Hemingway", publicationYear = 1929, isbn = "1232323-21", price = 16.99),
            Book(bookId = null, title = "Animal Farm", author = "George Orwell", publicationYear = 1945, isbn = "2212343-5", price = 14.99)
        )
        model.addAttribute("books", books)
        return "index"
    }

    @GetMapping("/add")
    fun addBook(model: Model): String {
        model.addAttribute("book", Book())
        model.addAttribute("categories", categoryRepository.findAll())
        return "addbook"
    }

    @RequestMapping("/delete/{id}")
    fun deleteBook(@PathVariable id: Long, model: Model): String {
        bookRepository.deleteById(id)
        return "redirect:/booklist"
    }

    @PostMapping("/save")
    fun save(book: Book): String {
        val category = categoryRepository.findById(book.categoryId!!).orElse(null)
        book.category = category
        bookRepository.save(book)
        return "redirect:/booklist"
    }

    @RequestMapping("/edit/{bookId}")
    fun editBook(@PathVariable bookId: Long, model: Model): String {
        model.addAttribute("book", bookRepository.findById(bookId).get())
        model.addAttribute("categories", categoryRepository.findAll())
        return "editbook"
    }

    @GetMapping("/booklist")
    fun booklist(model: Model): String {
        model.addAttribute("books", bookRepository.findAll())
        return "booklist"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}
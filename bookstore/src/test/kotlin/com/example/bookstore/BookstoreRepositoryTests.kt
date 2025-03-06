package com.example.bookstore


import com.example.bookstore.domain.Book
import com.example.bookstore.domain.Category
import com.example.bookstore.domain.User
import com.example.bookstore.domain.BookRepository
import com.example.bookstore.domain.CategoryRepository
import com.example.bookstore.domain.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
class BookstoreRepositoryTests {

    @Autowired
    lateinit var bookRepository: BookRepository
    
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    
    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup() {
        val fiction = Category(categoryId = null, name = "Fiction")
        val nonFiction = Category(categoryId = null, name = "Non-Fiction")
        categoryRepository.saveAll(listOf(fiction, nonFiction))

        val books = listOf(
            Book(
                bookId = null,
                title = "Book 1",
                author = "Author 1",
                publicationYear = 2020,
                isbn = "123",
                price = 1.2,
                category = fiction
            ),
            Book(
                bookId = null,
                title = "Book 2",
                author = "Author 2",
                publicationYear = 2021,
                isbn = "456",
                price = 2.3,
                category = fiction
            ),
            Book(
                bookId = null,
                title = "Book 3",
                author = "Author 3",
                publicationYear = 2022,
                isbn = "789",
                price = 3.4,
                category = nonFiction
            )
        )
        bookRepository.saveAll(books)

        val users = listOf(
            User(
                id = null,
                username = "user1",
                passwordHash = "password1",
                email = "user1@example.com",
                role = "USER"
            ),
            User(
                id = null,
                username = "user2",
                passwordHash = "password2",
                email = "user2@example.com",
                role = "ADMIN"
            )
        )
        userRepository.saveAll(users)


    }

    @Test
    fun contextLoads() {
        assertNotNull(bookRepository)
        assertNotNull(categoryRepository)
        assertNotNull(userRepository)
    }

    @Test
    fun `Assert book list is returned`() {
        val books = bookRepository.findAll()
        assertEquals(3, books.count())
    }
    
    @Test
    fun `Assert category list is returned`() {
        val categories = categoryRepository.findAll()
        assertEquals(2, categories.count())
    }

    @Test
    fun `Assert user list is returned`() {
        val users = userRepository.findAll()
        assertEquals(2, users.count())
    }

    @Test
    fun `Create new book`() {
        val category = categoryRepository.findAll().first()
        val book = Book(
            bookId = null, 
            title = "New Book", 
            author = "New Author", 
            publicationYear = 2021, 
            isbn = "123", 
            price = 1.2,
            category = category
        )
        bookRepository.save(book)
        val books = bookRepository.findAll()
        assertNotNull(books.find { it.title == "New Book" })
    }

    @Test
    fun `Create new category`() {
        val category = Category(categoryId = null, name = "New Category")
        categoryRepository.save(category)
        val categories = categoryRepository.findAll()
        assertNotNull(categories.find { it.name == "New Category" })
    }

    @Test
    fun `Create new user`() {
        val user = User(
            id = null,
            username = "New User", 
            passwordHash = "123", 
            email = "test@example.com", 
            role = "USER"
        )
        userRepository.save(user)
        val users = userRepository.findAll()
        assertNotNull(users.find { it.username == "New User" })
    }
@Test
fun `Delete book`() {
    val initialCount = bookRepository.count()
    val book = bookRepository.findAll().toList().first()  // Convert to list first
    bookRepository.delete(book)
    val finalCount = bookRepository.count()  // Use count() from repository
    assertEquals(initialCount - 1, finalCount)
}

@Test
fun `Delete category`() {
    val category = categoryRepository.findAll().toList().first()
    
    val booksWithCategory = bookRepository.findAll().toList().filter { it.category?.categoryId == category.categoryId }
    
    if (booksWithCategory.isNotEmpty()) {
        booksWithCategory.forEach { bookRepository.delete(it) }

    val initialCount = categoryRepository.count()
    categoryRepository.delete(category)
    val finalCount = categoryRepository.count()
    
    assertEquals(initialCount - 1, finalCount)
}

@Test
fun `Delete user`() {
    val initialCount = userRepository.count()
    val user = userRepository.findAll().toList().first()  // Convert to list first
    userRepository.delete(user)
    val finalCount = userRepository.count()  // Use count() from repository
    assertEquals(initialCount - 1, finalCount)
}
}
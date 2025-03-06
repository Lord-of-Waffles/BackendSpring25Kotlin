package com.example.bookstore

import com.example.bookstore.web.BookController

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookstoreRestTests(@Autowired val restTemplate: TestRestTemplate) {

    @Autowired
    lateinit var bookController: BookController

    @Test
    fun contextLoads() {
        assertNotNull(bookController)
    }

    @Test
    fun `Assert book list is returned`() {
        val entity: ResponseEntity<String> = restTemplate.getForEntity("/books", String::class.java)
        assertEquals(HttpStatus.OK, entity.statusCode)
        // Additional assertions can be added here
    }

    @Test
    fun `Assert book details are returned`() {
        val entity: ResponseEntity<String> = restTemplate.getForEntity("/books/1", String::class.java)
        assertEquals(HttpStatus.OK, entity.statusCode)
        // Additional assertions can be added here
    }
}
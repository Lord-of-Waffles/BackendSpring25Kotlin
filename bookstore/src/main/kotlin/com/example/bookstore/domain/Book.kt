package com.example.bookstore.domain

import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,  // Nullable ID for auto-generation

    var title: String,
    var author: String,
    var publicationYear: Int,
    var isbn: String,
    var price: Double
)

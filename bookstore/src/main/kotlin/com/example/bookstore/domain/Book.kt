package com.example.bookstore.domain

import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var bookId: Long? = null,

    var title: String = "",
    var author: String = "",
    var publicationYear: Int = 0,
    var isbn: String = "",
    var price: Double = 0.0,

    @Transient
    var categoryId: Long? = null,

    @ManyToOne @JoinColumn(name = "category_id")
    var category: Category? = null
) {
    constructor() : this(null, "", "", 0, "", 0.0, null, null)
}
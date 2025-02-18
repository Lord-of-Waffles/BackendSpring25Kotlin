package com.example.bookstore.domain

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var categoryId: Long? = null,  // Nullable ID for auto-generation

    var name: String = ""
) {
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var books: List<Book> = mutableListOf()
}
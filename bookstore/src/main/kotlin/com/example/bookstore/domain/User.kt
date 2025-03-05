package com.example.bookstore.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(unique = true)
    val username: String,

    val passwordHash: String,

    val email: String,

    val role: String
) {
    constructor() : this(
        id = null,
        username = "",
        passwordHash = "",
        email = "",
        role = ""
    )
}
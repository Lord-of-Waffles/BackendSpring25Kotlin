package com.example.bookstore.domain

import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Long>
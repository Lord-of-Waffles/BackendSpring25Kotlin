package com.example.homework1worton

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContactController {
    @GetMapping("/contact")
    fun contact(): String {
        return "This is the contact page"
    }
}
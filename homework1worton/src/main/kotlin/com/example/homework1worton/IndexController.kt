package com.example.homework1worton

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    @GetMapping("/index")
    fun index(): String {
        return "This is the main page"
    }
}
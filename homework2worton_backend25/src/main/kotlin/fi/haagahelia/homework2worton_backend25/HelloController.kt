package fi.haagahelia.homework2worton_backend25

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

//This is the file for the first task of the homework, "Hello Thymeleaf"

@Controller
class HelloController {
    @GetMapping("/hello")
    fun hello(
        @RequestParam name: String,
        @RequestParam age: String,
        model: Model
    ): String {
        model.addAttribute("name", name)
        model.addAttribute("age", age)
        return "hello"
    }
}


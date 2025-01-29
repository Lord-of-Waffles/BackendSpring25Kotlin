package fi.haagahelia.homework2worton_backend25_part2.web

import fi.haagahelia.homework2worton_backend25_part2.domain.Student
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StudentController {

    @GetMapping("/hello")
    fun hello(model: Model): String {
        val students = listOf(
            Student("Kate", "Cole"),
            Student("Dan", "Brown"),
            Student("Mike", "Mars")
        )
        model.addAttribute("students", students)
        return "hello"
    }
}

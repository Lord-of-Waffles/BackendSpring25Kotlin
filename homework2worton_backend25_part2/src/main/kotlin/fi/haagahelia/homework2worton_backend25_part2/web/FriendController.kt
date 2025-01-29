import fi.haagahelia.homework2worton_backend25_part2.domain.Friend
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute



@Controller
class FriendController {

    val friends = mutableListOf(
        Friend("Kate", "Cole"),
        Friend("Dan", "Brown"),
        Friend("Mike", "Mars")
    )
    
    @GetMapping("/friend")
    fun friend(model: Model): String {
        model.addAttribute("friends", friends)
        model.addAttribute("newFriend", Friend("", ""))  
        return "friend"
    }

    @PostMapping("/friend")
    fun addFriend(@ModelAttribute newFriend: Friend): String {
        friends.add(newFriend)
        return "redirect:/friend"
    }
}
package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoggedUserController {
    private final UserService userService;
    public LoggedUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loggedUser")
    public String home(final Model model,
                       final HttpSession session) {
        String email = session.getAttribute("userEmail").toString();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", email);
        model.addAttribute("password", user.getPassword());
        model.addAttribute("loginStatus", session.getAttribute("loginStatus").toString());
        return "loggedUserHome";
    }
}

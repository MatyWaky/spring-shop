package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignOutController {

    /*@GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }*/
}

package matywaky.com.github.springshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoggedUserController {

    @GetMapping("/loggedUser")
    public String home() {
        return "loggedUserHome";
    }
}

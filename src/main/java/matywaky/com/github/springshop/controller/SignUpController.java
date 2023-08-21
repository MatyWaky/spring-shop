package matywaky.com.github.springshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping
    public String idk() {

    }
}

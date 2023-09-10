package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.service.user.UserService;
import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    private final UserService userService;

    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signInForm() {
        return "sign-in";
    }

    @PostMapping("/signin/save")
    public String signIn(@Valid @ModelAttribute("user") UserDto userDto,
                         BindingResult result,
                         Model model,
                         HttpServletResponse response) {
        String error = userService.checkSignInData(userDto);
        if (error != null) {
            model.addAttribute("errorMessage", error);
            return "sign-in";
        }

        model.addAttribute("loginStatus", "logged");
        Cookie welcomeCookie = new Cookie("welcome", userDto.getEmail());
        welcomeCookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(welcomeCookie);
        return "redirect:/loggedUser";
    }
}

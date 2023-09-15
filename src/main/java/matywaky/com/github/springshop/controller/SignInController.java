package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.model.User;
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

    @GetMapping("/sign-in")
    public String signInForm() {
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute("user") UserDto userDto,
                         BindingResult result,
                         Model model,
                         final HttpSession session) {
        String error = userService.checkSignInData(userDto);
        if (error != null) {
            model.addAttribute("errorMessage", error);
            return "sign-in";
        }

        User user = userService.findUserByEmail(userDto.getEmail());
        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", userDto.getEmail());
        session.setAttribute("loginStatus", "logged");
        return "redirect:/";
    }
}

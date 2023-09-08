package matywaky.com.github.springshop.controller;

import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUpForm() {
        return "sign-up";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute("user") UserDto userDto,
                         BindingResult result,
                         Model model) {
        String error = userService.checkSignUpData(userDto);
        if (error != null) {
            model.addAttribute("errorMessage", error);
            return "sign-up";
        }

        /*
         * SPRAWDZIĆ POWTÓRZONE HASŁO W JS tzn.
         * Czy pole jest wypełnione?
         * Czy pole jest takie samo jak hasło?
         */
        userService.saveUser(userDto);
        return "redirect:/signup?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}

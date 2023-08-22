package matywaky.com.github.springshop.controller;

import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.model.User;
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

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUpForm() {
        // tu niby brakuje Model model itd.
        return "sign-up";
    }

    @PostMapping("/signup/save")
    public String signUp(@Valid @ModelAttribute("user") UserDto userDto,
                         BindingResult result,
                         Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null &&
        existingUser.getEmail() != null &&
        !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/sign-up";
        }

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

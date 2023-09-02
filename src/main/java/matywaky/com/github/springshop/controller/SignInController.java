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

@Controller
public class SignInController {

    private UserService userService;

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
                         Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser == null ||
                existingUser.getEmail() == null ||
                existingUser.getEmail().isEmpty() ||
                !existingUser.getPassword().equals(userDto.getPassword())) {
            return "redirect:/sign-in?wrongData";
            /*result.rejectValue("all", "666",
                    "Wrong email and/or password or account does not exist");*/
        }
        return "redirect:/sign-in?success";
    }
}

package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.model.UserDetails;
import matywaky.com.github.springshop.service.user.UserService;
import matywaky.com.github.springshop.service.userDetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoggedUserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public LoggedUserController(UserService userService,
                                UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
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

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/settings")
    public String editData(@Valid @ModelAttribute("userDetails") UserDetailsDto userDetailsDto,
                           Model model,
                           final HttpSession session) {
        Long id = Long.parseLong(session.getAttribute("userId").toString());
        userDetailsService.editData(id, userDetailsDto);
        return "redirect:/settings?success";
    }
}

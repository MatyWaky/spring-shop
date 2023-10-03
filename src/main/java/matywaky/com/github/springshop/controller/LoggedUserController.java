package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.service.user.UserService;
import matywaky.com.github.springshop.service.userDetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
    public String home(@CookieValue(value = "user") String email,
                       @CookieValue(value = "status") String status,
                       final HttpSession session) {
        User user = userService.findUserByEmail(email);
        if (user != null && !user.getEmail().isEmpty())
            session.setAttribute("user", email);

        if (status != null && !status.isEmpty())
            session.setAttribute("status", status);

        return "loggedUserHome";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/settings")
    public String editData(@Valid @ModelAttribute("userDetails") UserDetailsDto userDetailsDto,
                           final HttpSession session) {
        Long id = Long.parseLong(session.getAttribute("userId").toString());
        userDetailsService.editData(id, userDetailsDto);
        return "redirect:/settings?success";
    }
}

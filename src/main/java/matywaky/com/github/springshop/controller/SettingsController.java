package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.service.userDetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingsController {

    private final UserDetailsService userDetailsService;

    public SettingsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/settings")
    public String editData(@Valid @ModelAttribute("userDetails") UserDetailsDto userDetailsDto,
                           @CookieValue(value = "user", defaultValue = "") String email) {
        if (!email.isEmpty())
            userDetailsService.editData(userDetailsDto, email);

        return "redirect:/settings?success";
    }
}

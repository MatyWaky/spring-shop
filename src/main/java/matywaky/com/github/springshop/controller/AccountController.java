package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.RoleDto;
import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.service.account.AccountService;
import matywaky.com.github.springshop.service.role.RoleService;
import matywaky.com.github.springshop.service.user.UserService;
import matywaky.com.github.springshop.service.userDetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class AccountController {

    private final UserDetailsService userDetailsService;
    private final RoleService roleService;
    private final UserService userService;
    private final AccountService accountService;

    public AccountController(UserDetailsService userDetailsService,
                             RoleService roleService,
                             UserService userService,
                             AccountService accountService) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String account() {
        return "account";
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

    @GetMapping("/add-user")
    public String addUser(final Model model) {
        List<RoleDto> roleDtos = roleService.findAllRoles();
        List<String> roles = new ArrayList<>();
        for (RoleDto  rd : roleDtos)
            roles.add(rd.getName());

        model.addAttribute("roles", roles);
        return "add-user";
    }

    @PostMapping("/add-user")
    public String createNewUser(@Valid @ModelAttribute("user") UserDto userDto,
                                Model model) {
        String error = userService.checkSignUpData(userDto);
        if (error != null) {
            model.addAttribute("errorMessage", error);
            return "redirect:/add-user?error";
        }

        userService.saveUser(userDto);
        return "redirect:/add-user?success";
    }

    @GetMapping("/order-history")
    public String orderHistory(final HttpSession httpSession,
                               final Model model) {
        model.addAttribute("orders", accountService.findAllOrdersByIds((String) httpSession.getAttribute("user")));
        return "order-history";
    }
}

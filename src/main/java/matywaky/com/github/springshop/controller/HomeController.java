package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.service.product.ProductService;
import matywaky.com.github.springshop.service.user.UserService;
import matywaky.com.github.springshop.service.userDetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    public HomeController(ProductService productService,
                          UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(@CookieValue(value = "user", defaultValue = "") String email,
                       @CookieValue(value = "status", defaultValue = "") String status,
                       @CookieValue(value = "role", defaultValue = "") String role,
                       Model model,
                       HttpSession session) {
        if (!email.isEmpty()) {
            User user = userService.findUserByEmail(email);
            if (user != null) {
                session.setAttribute("user", email);
                session.setAttribute("role", role);
            }
        }

        if (status != null && !status.isEmpty())
            session.setAttribute("status", status);

        model.addAttribute("products", productService.findAllProducts());
        if (session.getAttribute("loginStatus") != null)
            model.addAttribute("loginStatus", "logged");

        return "home";
    }
}

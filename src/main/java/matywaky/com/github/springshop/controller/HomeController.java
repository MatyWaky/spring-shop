package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import matywaky.com.github.springshop.ProductOperation;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.service.cart.CartService;
import matywaky.com.github.springshop.service.product.ProductService;
import matywaky.com.github.springshop.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    public HomeController(ProductService productService,
                          UserService userService,
                          CartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
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

    @GetMapping("/add/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId,
                                   final HttpServletRequest request) {
        cartService.productOperation(productId, ProductOperation.INCREASE);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}

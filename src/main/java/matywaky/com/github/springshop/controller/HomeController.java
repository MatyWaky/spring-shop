package matywaky.com.github.springshop.controller;

import jakarta.servlet.http.HttpSession;
import matywaky.com.github.springshop.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("products", productService.findAllProducts());
        if (session.getAttribute("loginStatus") != null)
            model.addAttribute("loginStatus", "logged");

        return "home";
    }
}

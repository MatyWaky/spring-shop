package matywaky.com.github.springshop.controller;

import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{productName}")
    public String detailsPage(@PathVariable("productName") String productName,
                              final Model model) {
        model.addAttribute("product", productService.findProductByName(productName));
        return "product-details";
    }
}

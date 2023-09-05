package matywaky.com.github.springshop.controller;

import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.ProductDto;
import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addProduct(@Valid @ModelAttribute("product")ProductDto productDto) {
        //Product existingProduct = productService.findProductByName(productDto.getName());
        // sprawdzenie, czy produkt istnieje if (existingProduct == null ||)
        productService.saveProduct(productDto);

        return "redirect:/admin?success";
    }
}

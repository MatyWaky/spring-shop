package matywaky.com.github.springshop.controller;

import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.ProductDto;
import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String addProduct(@Valid @ModelAttribute("product")ProductDto productDto,
                             BindingResult bindingResult,
                             Model model) {
        Product existingProduct = productService.findProductByName(productDto.getName());
        if (existingProduct != null &&
                existingProduct.getName() != null &&
                !existingProduct.getName().isEmpty()) {
            bindingResult.rejectValue("name", null,
                    "There is already an product registered with the same name");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("name", productDto);
            return "/admin";
        }

        productService.saveProduct(productDto);

        return "redirect:/admin?success";
    }
}

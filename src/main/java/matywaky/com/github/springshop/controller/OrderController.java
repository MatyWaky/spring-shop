package matywaky.com.github.springshop.controller;

import matywaky.com.github.springshop.service.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    //private final OrderService orderService;
    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String showCart() {
        return "cartView";
    }
}

package matywaky.com.github.springshop.controller;

import matywaky.com.github.springshop.dto.OrderDto;
import matywaky.com.github.springshop.service.cart.CartService;
import matywaky.com.github.springshop.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    public OrderController(CartService cartService,
                           OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }


    @GetMapping("/cart")
    public String showCart() {
        return "cartView";
    }

    @GetMapping("/summary")
    public String showSummary() {
        return "summary";
    }

    @PostMapping("/saveorder")
    public String saveOrder(OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "redirect:/";
    }
}
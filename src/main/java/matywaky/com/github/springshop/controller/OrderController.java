package matywaky.com.github.springshop.controller;

import matywaky.com.github.springshop.ProductOperation;
import matywaky.com.github.springshop.dto.OrderDto;
import matywaky.com.github.springshop.service.cart.CartService;
import matywaky.com.github.springshop.service.order.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String publicKey;
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
    public String showSummary(OrderDto orderDto,
                              Long id,
                              final Model model) {
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("orderId", id);
        model.addAttribute("publicKey", publicKey);
        return "summary";
    }

    /*@PostMapping("/saveorder")
    public String saveOrder(@CookieValue(value = "user", defaultValue = "") String email,
                            OrderDto orderDto,
                            final Model model) {
        String error = orderService.saveOrder(orderDto, email);
        if (error != null) {
            model.addAttribute("error", error);
            return "summary";
        }

        return "redirect:/";
    }*/

    @GetMapping("/orderdetails")
    public String orderDetails() {
        return "order-details";
    }

    @PostMapping("/orderdetails")
    public String checkDeliveryData(@CookieValue(value = "user", defaultValue = "") String email,
                                    OrderDto orderDto,
                                    final Model model,
                                    final RedirectAttributes redirectAttributes) {
        String error = orderService.checkData(orderDto);
        if (error != null) {
            model.addAttribute("error", error);
            return "order-details";
        }

        Long id = orderService.saveOrder(orderDto, email);
        /*redirectAttributes.addFlashAttribute("orderDto", orderDto);
        redirectAttributes.addFlashAttribute("orderId", id);*/

        model.addAttribute("orderDto", orderDto);
        model.addAttribute("orderId", id);
        model.addAttribute("publicKey", publicKey);
        return "summary";
    }

    @GetMapping("/increase/{productId}")
    public String increaseProduct(@PathVariable("productId") Long productId) {
        cartService.productOperation(productId, ProductOperation.INCREASE);
        return "cartView";
    }

    @GetMapping("/decrease/{productId}")
    public String decreaseProduct(@PathVariable("productId") Long productId) {
        cartService.productOperation(productId, ProductOperation.DECREASE);
        return "cartView";
    }

    @GetMapping("/remove/{productId}")
    public String removeProductsFromCart(@PathVariable("productId") Long productId) {
        cartService.productOperation(productId, ProductOperation.REMOVE);
        return "cartView";
    }
}

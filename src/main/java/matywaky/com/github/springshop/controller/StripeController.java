package matywaky.com.github.springshop.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.validation.Valid;
import matywaky.com.github.springshop.dto.stripe.ChargeRequest;
import matywaky.com.github.springshop.dto.stripe.ChargeRequestDto;
import matywaky.com.github.springshop.service.stripe.StripeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StripeController {

    private final  StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/order/charge")
    public String charge(@Valid @ModelAttribute("ChargeRequestDto") ChargeRequestDto chargeRequestDto,
                         final Model model)
            throws StripeException {
        /*chargeRequest.setDescription("Payment for order #order id here");*/
        chargeRequestDto.setCurrency("pln");
        ChargeRequest chargeRequest = stripeService.chargeRequestMapper(chargeRequestDto);
        Charge charge = stripeService.charge(chargeRequest);
        model.addAttribute("charge", charge);
        return "payment-data";
    }

/*    @GetMapping("/charge")
    public String chargeForm() {
        return "charge";
    }*/
}

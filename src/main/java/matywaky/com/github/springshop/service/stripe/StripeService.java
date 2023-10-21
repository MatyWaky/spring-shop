package matywaky.com.github.springshop.service.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import matywaky.com.github.springshop.Cart;
import matywaky.com.github.springshop.dto.stripe.ChargeRequest;
import matywaky.com.github.springshop.dto.stripe.ChargeRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;
    private final Cart cart;

    public StripeService(Cart cart) {
        this.cart = cart;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }

    public ChargeRequest chargeRequestMapper(ChargeRequestDto chargeRequestDto) {
        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setDescription(chargeRequestDto.getDescription());
        chargeRequest.setAmount(chargeRequestDto.getAmount().multiply(BigDecimal.valueOf(100)).intValue());
        chargeRequest.setCurrency(chargeRequestDto.getCurrency());
        chargeRequest.setStripeEmail(chargeRequestDto.getStripeEmail());
        chargeRequest.setStripeToken(chargeRequestDto.getStripeToken());
        return chargeRequest;
    }

    public void clearCart() {
        cart.clearCart();
    }
}

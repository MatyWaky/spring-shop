package matywaky.com.github.springshop.dto.stripe;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeRequest {
    private String description;
    private int amount;
    private String currency;
    private String stripeEmail;
    private String stripeToken;
}

package matywaky.com.github.springshop.dto.stripe;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeRequestDto {
    private String description;
    private BigDecimal amount;
    private String currency;
    private String stripeEmail;
    private String stripeToken;
}

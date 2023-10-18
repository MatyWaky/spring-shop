package matywaky.com.github.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String postCode;
    private String city;
    private String country;
}

package matywaky.com.github.springshop.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;
    @NotEmpty(message = "Product name should not be empty")
    private String name;

    @NotEmpty(message = "Product description should not be empty")
    private String description;

    @NotEmpty(message = "Product image should not be empty")
    private String image;

    //@NotEmpty(message = "Product price should not be empty")
    private float price;

    private Integer quantity;
}

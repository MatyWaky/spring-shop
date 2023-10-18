package matywaky.com.github.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.model.order.OrderProduct;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderHistoryDetailsDto {
    private Order order;
    private List<OrderProduct> orderProduct;
    private Map<Product, Integer> productAndAmount;
}

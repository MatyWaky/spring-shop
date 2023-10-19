package matywaky.com.github.springshop.service.order;

import matywaky.com.github.springshop.dto.OrderDto;

public interface OrderService {
    String saveOrder(OrderDto orderDto, String email);
}

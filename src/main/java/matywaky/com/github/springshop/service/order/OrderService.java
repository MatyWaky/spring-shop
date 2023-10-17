package matywaky.com.github.springshop.service.order;

import matywaky.com.github.springshop.dto.OrderDto;

public interface OrderService {

    void saveOrder(OrderDto orderDto);
    void orderHistory(OrderDto orderDto, String email);
}

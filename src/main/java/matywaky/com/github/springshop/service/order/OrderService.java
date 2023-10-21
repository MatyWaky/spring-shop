package matywaky.com.github.springshop.service.order;

import matywaky.com.github.springshop.dto.OrderDto;

public interface OrderService {
    Long saveOrder(OrderDto orderDto, String email);
    String checkData(OrderDto orderDto);
}

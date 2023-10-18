package matywaky.com.github.springshop.service.account;

import matywaky.com.github.springshop.dto.OrderHistoryDetailsDto;
import matywaky.com.github.springshop.model.order.Order;

import java.util.List;
import java.util.Set;

public interface AccountService {
    List<Order> findAllOrdersByIds(String email);
    OrderHistoryDetailsDto orderHistoryDetailsByOrderId(Long orderId);
}

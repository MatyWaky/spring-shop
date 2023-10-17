package matywaky.com.github.springshop.service.account;

import matywaky.com.github.springshop.model.order.Order;

import java.util.Set;

public interface AccountService {
    Set<Order> findAllOrdersByIds(String email);
}

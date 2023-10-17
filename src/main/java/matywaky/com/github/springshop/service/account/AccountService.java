package matywaky.com.github.springshop.service.account;

import matywaky.com.github.springshop.model.order.Order;

import java.util.Set;

public interface AccountService {

    public Set<Order> findAllOrders(String email);
}

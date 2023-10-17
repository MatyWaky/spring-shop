package matywaky.com.github.springshop.service.account;

import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.repository.UserRepository;
import matywaky.com.github.springshop.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AccountServiceImpl(UserRepository userRepository,
                              OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Order> findAllOrders(String email) {
        User user = userRepository.findByEmail(email);
        Set<Order> orderIds = orderRepository.findOrdersByUserId(user.getId());
        return orderIds;
    }
}

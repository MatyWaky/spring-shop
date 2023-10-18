package matywaky.com.github.springshop.service.account;

import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.repository.UserRepository;
import matywaky.com.github.springshop.repository.order.OrderRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public List<Order> findAllOrdersByIds(String email) {
        User user = userRepository.findByEmail(email);
        Set<Long> ids = orderRepository.findAllOrdersByUserID(user.getId());
        List<Order> orders = new ArrayList<>();
        for (Long id : ids) {
            orders.add(orderRepository.findOrderByOrderId(id));
        }

        Collections.sort(orders);
        return orders;
    }
}

package matywaky.com.github.springshop.repository.order;

import matywaky.com.github.springshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Set<Order> findOrdersByUserId(Long userId);
}

package matywaky.com.github.springshop.repository.order;

import matywaky.com.github.springshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

package matywaky.com.github.springshop.repository.order;

import matywaky.com.github.springshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByOrderId(Long id);
    @Query(value = "SELECT order_id FROM users_orders uo WHERE uo.user_id = ?1",
            nativeQuery = true)
     Set<Long> findAllOrdersByUserID(Long id);
}

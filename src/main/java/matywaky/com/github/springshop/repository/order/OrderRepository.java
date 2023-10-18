package matywaky.com.github.springshop.repository.order;

import matywaky.com.github.springshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByOrderId(Long id);
    @Query(value = "SELECT order_id FROM users_orders uo " +
            "WHERE uo.user_id = ?1 " +
            "ORDER BY order_id DESC",
            nativeQuery = true)
     Set<Long> findAllOrdersByUserID(Long id);

    @Query(value = "SELECT SUM(p.price*op.amount) " +
            "FROM Products p INNER JOIN order_product op ON p.id = op.product_id " +
            "INNER JOIN orders o ON o.order_id = op.order_id " +
            "WHERE o.order_id = ?1",
            nativeQuery = true)
    BigDecimal totalToPayInOrder(Long id);
}

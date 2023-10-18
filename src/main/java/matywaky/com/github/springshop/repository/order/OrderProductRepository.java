package matywaky.com.github.springshop.repository.order;

import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.model.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findOrderProductByOrderId(Long orderId);
}

package matywaky.com.github.springshop.repository.order;

import matywaky.com.github.springshop.model.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}

package matywaky.com.github.springshop.service.order;

import matywaky.com.github.springshop.Cart;
import matywaky.com.github.springshop.CartProduct;
import matywaky.com.github.springshop.dto.OrderDto;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.model.order.OrderProduct;
import matywaky.com.github.springshop.repository.order.OrderProductRepository;
import matywaky.com.github.springshop.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderServiceImpl(Cart cart,
                            OrderRepository orderRepository,
                            OrderProductRepository orderProductRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        Order order = orderMapper(orderDto);
        orderRepository.save(order);
        orderProductRepository.saveAll(mapToOrderProductList(cart, order));
        cart.clearCart();
    }

    private Order orderMapper(OrderDto orderDto) {
        return new Order(orderDto.getFirstName(),
                orderDto.getLastName(),
                orderDto.getAddress(),
                orderDto.getPostCode(),
                orderDto.getCity(),
                LocalDateTime.now());
    }

    private List<OrderProduct> mapToOrderProductList(Cart cart, Order order) {
        List<OrderProduct> orderItems = new ArrayList<>();
        for (CartProduct cp : cart.getCartProducts()) {
            orderItems.add(new OrderProduct(order.getOrderId(), cp.getProduct().getId(), cp.getCounter()));
        }
        return orderItems;
    }
}

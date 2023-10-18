package matywaky.com.github.springshop.service.order;

import matywaky.com.github.springshop.Cart;
import matywaky.com.github.springshop.CartProduct;
import matywaky.com.github.springshop.dto.OrderDto;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.model.order.OrderProduct;
import matywaky.com.github.springshop.repository.UserRepository;
import matywaky.com.github.springshop.repository.order.OrderProductRepository;
import matywaky.com.github.springshop.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(Cart cart,
                            OrderRepository orderRepository,
                            OrderProductRepository orderProductRepository,
                            UserRepository userRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.userRepository = userRepository;
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
                orderDto.getPhoneNumber(),
                orderDto.getAddress(),
                orderDto.getPostCode(),
                orderDto.getCity(),
                orderDto.getCountry(),
                LocalDateTime.now());
    }

    private List<OrderProduct> mapToOrderProductList(Cart cart, Order order) {
        List<OrderProduct> orderItems = new ArrayList<>();
        for (CartProduct cp : cart.getCartProducts()) {
            orderItems.add(new OrderProduct(order.getOrderId(), cp.getProduct().getId(), cp.getCounter()));
        }
        return orderItems;
    }

    public void orderHistory(OrderDto orderDto, String email) {
        Order order = orderMapper(orderDto);
        User user = userRepository.findByEmail(email);
        order.getUsers().add(user);
        orderRepository.save(order);
        orderProductRepository.saveAll(mapToOrderProductList(cart, order));
        order.setTotalToPay(orderRepository.totalToPayInOrder(order.getOrderId()));
        cart.clearCart();
    }
}

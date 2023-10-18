package matywaky.com.github.springshop.service.account;

import matywaky.com.github.springshop.dto.OrderHistoryDetailsDto;
import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.model.order.Order;
import matywaky.com.github.springshop.model.order.OrderProduct;
import matywaky.com.github.springshop.repository.ProductRepository;
import matywaky.com.github.springshop.repository.UserRepository;
import matywaky.com.github.springshop.repository.order.OrderProductRepository;
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
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public AccountServiceImpl(UserRepository userRepository,
                              OrderRepository orderRepository,
                              OrderProductRepository orderProductRepository,
                              ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
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

    @Override
    public OrderHistoryDetailsDto orderHistoryDetailsByOrderId(Long orderId) {
        OrderHistoryDetailsDto orderHistoryDetailsDto = new OrderHistoryDetailsDto();
        Order order = orderRepository.findOrderByOrderId(orderId);
        orderHistoryDetailsDto.setOrder(order);
        List<OrderProduct> orderProducts = orderProductRepository.findOrderProductByOrderId(orderId);
        orderHistoryDetailsDto.setOrderProduct(orderProducts);

        Map<Product, Integer> pAI = new HashMap<>();
        for (OrderProduct op : orderProducts) {
            pAI.put(productRepository.findProductById(op.getProductId()), op.getAmount());
        }
        orderHistoryDetailsDto.setProductAndAmount(pAI);

        return orderHistoryDetailsDto;
    }
}

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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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

    @Override
    public String saveOrder(OrderDto orderDto, String email) {
        String error;
        if (orderDto.getCity() == null || orderDto.getCountry() == null) {
            orderDto = readDataFromMap(orderDto);
            error = "Cannot find place by post code! Please fill all fields";
            if (orderDto == null)
                return error;
        }

        error = checkUserData(orderDto);
        if (error != null)
            return error;

        Order order = orderMapper(orderDto);
        User user = userRepository.findByEmail(email);
        order.getUsers().add(user);
        orderRepository.save(order);
        orderProductRepository.saveAll(mapToOrderProductList(cart, order));
        order.setTotalToPay(orderRepository.totalToPayInOrder(order.getOrderId()));
        cart.clearCart();


        return null;
    }

    private OrderDto readDataFromMap(OrderDto orderDto) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> topic_body = restTemplate.exchange(
                "https://nominatim.openstreetmap.org/?addressdetails=1&q="+orderDto.getPostCode()+"&format=json&limit=1",
                HttpMethod.GET, null, String.class );

        String topicBody = topic_body.getBody();
        if (topicBody == null || topic_body.getBody().isEmpty())
            return null;

        topicBody = topicBody.replace("\"address\":{", "");
        topicBody = topicBody.replace("[","");
        topicBody = topicBody.replace("]","");
        topicBody = topicBody.replace("}}","");
        topicBody = topicBody.replace("{","");

        if (topicBody.isEmpty())
            return null;

        String[] list = topicBody.split(",\"");
        boolean shouldCheck = true;
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i].replace("\"", "");

            String[] tempList = list[i].split(":");
            if ((tempList[0].equals("city") || tempList[0].equals("town") || tempList[0].equals("municipality"))
                    && shouldCheck) {
                orderDto.setCity(tempList[1]);
                shouldCheck = false;
            }
            if (tempList[0].equals("country"))
                orderDto.setCountry(tempList[1]);
        }

        return orderDto;
    }

    private String checkUserData(OrderDto orderDto) {
        if (orderDto.getFirstName().isEmpty() ||
        orderDto.getLastName().isEmpty() ||
        orderDto.getCountry().isEmpty() ||
        orderDto.getCity().isEmpty() ||
        orderDto.getAddress().isEmpty() ||
        orderDto.getPhoneNumber().isEmpty() ||
        orderDto.getPostCode().isEmpty()) {
            return "Some fields are empty!";
        }


        return null;
    }
}

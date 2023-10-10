package matywaky.com.github.springshop.model.order;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderProduct> orderProductsList;

    public Order(String firstName,
                 String lastName,
                 String address,
                 String postCode,
                 String city,
                 LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.created = created;
    }
}

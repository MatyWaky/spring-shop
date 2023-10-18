package matywaky.com.github.springshop.model.order;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matywaky.com.github.springshop.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String postCode;
    private String city;
    private String country;
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderProduct> orderProductsList;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "users_orders",
            joinColumns = {
                    @JoinColumn(name = "order_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private Set<User> users = new HashSet<>();

    public Order(String firstName,
                 String lastName,
                 String phoneNumber,
                 String address,
                 String postCode,
                 String city,
                 String country,
                 LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.created = created;
    }

    public Order(String firstName,
                 String lastName,
                 String phoneNumber,
                 String address,
                 String postCode,
                 String city,
                 String country,
                 LocalDateTime created,
                 Set<User> users) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.created = created;
        this.users = users;
    }
}

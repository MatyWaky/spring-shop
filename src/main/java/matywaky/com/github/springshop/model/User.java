package matywaky.com.github.springshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matywaky.com.github.springshop.model.order.Order;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Nie tworzy kolumny w db
    /*@Transient
    private String confirmPassword;*/

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    @ManyToMany(mappedBy = "users", cascade = { CascadeType.ALL })
    private Set<Order> orders = new HashSet<Order>();

    public User(String email, String password, Role role, Status status) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}

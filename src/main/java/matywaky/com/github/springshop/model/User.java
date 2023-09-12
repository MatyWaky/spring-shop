package matywaky.com.github.springshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    /*@Column
    private Integer permission_id = 3;*/
    /* 1 - administrator
     * 2 - employee
     * 3 - customer
     */
    @ManyToOne
    @JoinColumn(name="permission_id", nullable=false)
    private Permission permission;

    /*@Column
    private Integer status_id = 2;*/
    /* 1 - ok
     * 2 - not_confirmed
     * 3 - banned
     */

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

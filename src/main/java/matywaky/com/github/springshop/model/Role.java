package matywaky.com.github.springshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    //length powinno być,
    // żeby później u klienta nie było niespodzianek, bo default tworzy varchar 255
    @Column(nullable = false, unique = true, length = 250)
    private String description;

    @OneToMany(mappedBy="role")
    private Set<User> users;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

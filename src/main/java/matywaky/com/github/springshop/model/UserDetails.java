package matywaky.com.github.springshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "userDetails")
    private User user;

    private String name;
    private String surname;
/*    private String country;
    private String postalCode;
    private String city;
    private int phoneNumber;
    private int nip;*/

}

package matywaky.com.github.springshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matywaky.com.github.springshop.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailsDto {

    private Long id;
    private String name;
    private String surname;
}

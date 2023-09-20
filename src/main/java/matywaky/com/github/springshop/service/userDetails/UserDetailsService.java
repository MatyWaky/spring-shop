package matywaky.com.github.springshop.service.userDetails;

import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.model.UserDetails;
import org.springframework.context.annotation.Bean;

public interface UserDetailsService {

    void editData(Long id, UserDetailsDto userDetailsDto);

}

package matywaky.com.github.springshop.service.userDetails;

import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.model.UserDetails;

public interface UserDetailsService {

    void editData(Long id, UserDetailsDto userDetailsDto);
}

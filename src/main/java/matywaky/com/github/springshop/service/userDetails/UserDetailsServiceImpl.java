package matywaky.com.github.springshop.service.userDetails;

import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.model.UserDetails;
import matywaky.com.github.springshop.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public void editData(Long id, UserDetailsDto userDetailsDto) {
        UserDetails userDetailsToUpdate = userDetailsRepository.getReferenceById(id);
        userDetailsToUpdate.setName(userDetailsDto.getName());
        userDetailsToUpdate.setSurname(userDetailsDto.getSurname());
        userDetailsRepository.save(userDetailsToUpdate);
    }
}

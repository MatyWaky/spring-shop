package matywaky.com.github.springshop.service.userDetails;

import matywaky.com.github.springshop.dto.UserDetailsDto;
import matywaky.com.github.springshop.model.UserDetails;
import matywaky.com.github.springshop.repository.UserDetailsRepository;
import matywaky.com.github.springshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository,
                                  UserRepository userRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void editData(UserDetailsDto userDetailsDto, String email) {
        UserDetails userDetailsToUpdate = userDetailsRepository.getReferenceById(
                userRepository.findByEmail(email)
                        .getId());
        userDetailsToUpdate.setName(userDetailsDto.getName());
        userDetailsToUpdate.setSurname(userDetailsDto.getSurname());
        userDetailsRepository.save(userDetailsToUpdate);
    }
}

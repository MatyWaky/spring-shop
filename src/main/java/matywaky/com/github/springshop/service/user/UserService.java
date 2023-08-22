package matywaky.com.github.springshop.service.user;

import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}

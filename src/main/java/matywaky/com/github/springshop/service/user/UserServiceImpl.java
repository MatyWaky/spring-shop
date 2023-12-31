package matywaky.com.github.springshop.service.user;

import matywaky.com.github.springshop.dto.UserDto;
import matywaky.com.github.springshop.model.Role;
import matywaky.com.github.springshop.model.Status;
import matywaky.com.github.springshop.model.User;
import matywaky.com.github.springshop.model.UserDetails;
import matywaky.com.github.springshop.repository.RoleRepository;
import matywaky.com.github.springshop.repository.StatusRepository;
import matywaky.com.github.springshop.repository.UserDetailsRepository;
import matywaky.com.github.springshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final UserDetailsRepository userDetailsRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           StatusRepository statusRepository,
                           UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userDto.getRole() == null || userDto.getRole()/*.getName()*/.isEmpty()) {
            user.setRole(checkRole("USER", "Default permissions"));
        } else {
            user.setRole(checkRole(
                            userDto.getRole()/*.getName()*/,
                            userDto.getRole()/*.getDescription()*/));
        }
        user.setStatus(checkStatus("NOT_VERIFIED", "The account has not been verified yet"));
        userRepository.save(user);

        UserDetails userDetails = new UserDetails();
        userDetails.setUser(user);
        userDetailsRepository.save(userDetails);

        user.setUserDetails(userDetails);
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public String checkSignUpData(UserDto userDto) {
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null)
            return "There is already an account registered with this email";
        else if (!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "Password are not the same!";
        else if (!checkPassword(userDto.getPassword()))
            return "Password doesn't meet requirements!";

        return null;
    }

    @Override
    public String checkSignInData(UserDto userDto) {
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser == null ||
                !passwordEncoder.matches(userDto.getPassword(), existingUser.getPassword()))
            return "There is not an account registered with this email or wrong input data!";

        return null;
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private boolean checkPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[!@#$%^&*ó+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private Role checkRole(String name, String description) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name, description);
            roleRepository.save(role);
        }

        return role;
    }

    private Status checkStatus(String name, String description) {
        Status status = statusRepository.findByName(name);
        if (status == null) {
            status = new Status(name, description);
            statusRepository.save(status);
        }

        return status;
    }
}

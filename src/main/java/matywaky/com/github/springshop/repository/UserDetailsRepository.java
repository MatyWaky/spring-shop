package matywaky.com.github.springshop.repository;

import matywaky.com.github.springshop.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}

package matywaky.com.github.springshop.repository;

import matywaky.com.github.springshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // tutaj deklarujemy również inne funkcje niż w JpaRepository np.:
    User findByEmail(String email);
}

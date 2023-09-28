package matywaky.com.github.springshop.repository;

import matywaky.com.github.springshop.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByName(String name);
}

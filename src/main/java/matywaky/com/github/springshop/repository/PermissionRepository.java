package matywaky.com.github.springshop.repository;

import matywaky.com.github.springshop.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Permission findByName(String name);
}

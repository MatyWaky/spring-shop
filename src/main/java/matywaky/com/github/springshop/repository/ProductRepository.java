package matywaky.com.github.springshop.repository;

import matywaky.com.github.springshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByName(String name);
}

package matywaky.com.github.springshop.repository;

import jakarta.transaction.Transactional;
import matywaky.com.github.springshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByName(String name);
    Product findProductById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE products p " +
            "SET p.quantity = p.quantity - ?2 " +
            "WHERE p.id = ?1",
            nativeQuery = true)
    void reduceQuantity(Long id, int quantity);
}

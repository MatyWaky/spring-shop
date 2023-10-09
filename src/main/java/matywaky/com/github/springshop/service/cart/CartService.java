package matywaky.com.github.springshop.service.cart;

import matywaky.com.github.springshop.ProductOperation;
import matywaky.com.github.springshop.model.Product;

import java.util.List;

public interface CartService {

    List<Product> getAllProducts();
    void productOperation(Long productId, ProductOperation productOperation);
}

package matywaky.com.github.springshop.service.cart;

import matywaky.com.github.springshop.Cart;
import matywaky.com.github.springshop.ProductOperation;
import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;
    private final Cart cart;

    public CartServiceImpl(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void productOperation(Long productId, ProductOperation productOperation) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            switch (productOperation) {
                case INCREASE -> cart.addProduct(product);
                case DECREASE -> cart.decreaseProduct(product);
                case REMOVE -> cart.removeAllProducts(product);
                default -> throw new IllegalArgumentException();
            }
        }
    }
}

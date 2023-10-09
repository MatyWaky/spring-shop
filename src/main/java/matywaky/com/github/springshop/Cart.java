package matywaky.com.github.springshop;

import lombok.Getter;
import matywaky.com.github.springshop.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {

    private List<CartProduct> cartProducts = new ArrayList<>();
    private int counter = 0;
    private float sum = 0f;

    public void addProduct(Product product) {
        getCartProduct(product).ifPresentOrElse(
                CartProduct::increaseCounter,
                () -> cartProducts.add(new CartProduct(product))
        );
    }

    public void decreaseProduct(Product product) {

    }

    public void removeAllProducts(Product product) {

    }

    private Optional<CartProduct> getCartProduct(Product product) {
        return cartProducts.stream()
                .filter(i -> i.idEquals(product))
                .findFirst();
    }
}

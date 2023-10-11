package matywaky.com.github.springshop;

import lombok.Getter;
import matywaky.com.github.springshop.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart implements Serializable {

    private List<CartProduct> cartProducts = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addProduct(Product product) {
        getCartProduct(product).ifPresentOrElse(
                CartProduct::increaseCounter,
                () -> cartProducts.add(new CartProduct(product))
        );

        recalculatePriceAndCounter();
    }

    public void decreaseProduct(Product product) {
        Optional<CartProduct> optionalCartProduct = getCartProduct(product);
        if (optionalCartProduct.isPresent()) {
            CartProduct cartProduct = optionalCartProduct.get();
            cartProduct.decreaseCounter();
            if (cartProduct.hasZeroItems()) {
                removeAllProducts(product);
            } else {
                recalculatePriceAndCounter();
            }
        }
    }

    public void removeAllProducts(Product product) {
        cartProducts.removeIf(i -> i.idEquals(product));
        recalculatePriceAndCounter();
    }

    private Optional<CartProduct> getCartProduct(Product product) {
        return cartProducts.stream()
                .filter(i -> i.idEquals(product))
                .findFirst();
    }

    public void clearCart() {
        cartProducts.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }

    private void recalculatePriceAndCounter() {
        int tempCounter = 0;
        BigDecimal tempPrice = BigDecimal.ZERO;

        for (CartProduct cp : cartProducts) {
            tempCounter += cp.getCounter();
            tempPrice = tempPrice.add(cp.getPrice());
        }

        this.counter = tempCounter;
        this.sum = tempPrice;
    }
}

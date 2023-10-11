package matywaky.com.github.springshop;

import lombok.Getter;
import matywaky.com.github.springshop.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class CartProduct implements Serializable {

    private final Product product;
    private int counter;
    private BigDecimal price;

    public CartProduct(Product product) {
        this.product = product;
        this.counter = 1;
        this.price = product.getPrice();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public void decreaseCounter() {
        if (counter > 0) {
            counter--;
            recalculate();
        }
    }

    public boolean hasZeroItems() {
        return counter == 0;
    }

    private void recalculate() {
        price = product.getPrice().multiply(new BigDecimal(counter));
    }

    public boolean idEquals(Product product) {
        return this.product.getId().equals(product.getId());
    }
}

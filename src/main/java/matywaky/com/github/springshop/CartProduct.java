package matywaky.com.github.springshop;

import lombok.Getter;
import matywaky.com.github.springshop.model.Product;

import java.io.Serializable;

@Getter
public class CartProduct implements Serializable {

    private Product product;
    private int counter;
    private float price;

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
        price = product.getPrice() * counter;
    }

    public boolean idEquals(Product product) {
        return this.product.getId().equals(product.getId());
    }
}

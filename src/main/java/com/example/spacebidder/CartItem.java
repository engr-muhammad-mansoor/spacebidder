package com.example.spacebidder;

import com.example.spacebidder.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {
    private Product product;
    private int counter;
    private BigDecimal price;

    public CartItem(Product product) {
        this.product = product;
        this.counter = 1;
        this.price = product.getProductPrice();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public void decreaseCounter() {
        if (counter > 0 ) {
            counter--;
            recalculate();
        }
    }

    public boolean hasZeroItems() {
        return counter == 0;
    }

    private void recalculate() {
        price = product.getProductPrice().multiply(new BigDecimal(counter));
    }

    public boolean idEquals(Product product) {
        return this.product.getProductId().equals(product.getProductId());
    }
}


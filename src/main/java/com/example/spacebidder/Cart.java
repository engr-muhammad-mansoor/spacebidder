package com.example.spacebidder;

import com.example.spacebidder.model.Product;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Product product) {
        getCartItemByItem(product).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(product))
        );
        recalculatePriceAndCounter();
    }

    public void decreaseItem(Product product) {
        Optional<CartItem> oCartItem = getCartItemByItem(product);
        if(oCartItem.isPresent()){
            CartItem cartItem = oCartItem.get();
            cartItem.decreaseCounter();
            if(cartItem.hasZeroItems()) {
                removeAllItems(product);
            }else {
                recalculatePriceAndCounter();
            }
        }
    }

    public void removeAllItems(Product product) {
        cartItems.removeIf(i -> i.idEquals(product));
        recalculatePriceAndCounter();
    }

    private void recalculatePriceAndCounter() {
        sum = cartItems.stream().map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = cartItems.stream().mapToInt(CartItem::getCounter)
                .reduce(0, Integer::sum);
    }

    private Optional<CartItem> getCartItemByItem(Product product) {
        return cartItems.stream()
                .filter(i -> i.idEquals(product))
                .findFirst();
    }

    public void cleanCart() {
        cartItems.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }
}
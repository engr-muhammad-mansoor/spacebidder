package com.example.spacebidder.service;

import com.example.spacebidder.Cart;
import com.example.spacebidder.ProductOperation;
import com.example.spacebidder.model.Product;
import com.example.spacebidder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final Cart cart;

    @Autowired
    public CartService(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }

    public List<Product> getAllItems() {
        return productRepository.findAll();
    }

    public void itemOperation(Long itemId, ProductOperation productOperation) {
        Optional<Product> oItem = productRepository.findById(itemId);
        if (oItem.isPresent()) {
            Product product = oItem.get();
            switch (productOperation) {
                case INCREASE -> cart.addItem(product);
                case DECREASE -> cart.decreaseItem(product);
                case REMOVE -> cart.removeAllItems(product);
                default -> throw new IllegalArgumentException();
            }
        }
    }
}



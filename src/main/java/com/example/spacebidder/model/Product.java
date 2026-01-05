package com.example.spacebidder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private String imgUrl;
    private String productDesc;
    private int productAvailable;
    private String productCategory;
    private int stars;

    public Product(String name, BigDecimal price, String imgUrl, String desc, Integer available, String category) {
        this.productName = name;
        this.productPrice = price;
        this.imgUrl = imgUrl;
        this.productDesc = desc;
        this.productAvailable = available;
        this.productCategory = category;
    }
}
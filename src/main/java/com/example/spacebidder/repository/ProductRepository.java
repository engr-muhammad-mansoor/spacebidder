package com.example.spacebidder.repository;

import com.example.spacebidder.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductCategory(String automotive);

    @Query("SELECT DISTINCT p.productCategory FROM Product p")
    List<String> findDistinctCategories();
}
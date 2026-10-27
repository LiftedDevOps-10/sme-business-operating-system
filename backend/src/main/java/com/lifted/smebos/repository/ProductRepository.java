package com.lifted.smebos.repository;

import com.lifted.smebos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByStockQuantityLessThanEqual(Integer reorderLevel);
}
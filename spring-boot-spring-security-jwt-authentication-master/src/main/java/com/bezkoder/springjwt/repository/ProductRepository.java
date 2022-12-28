package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}

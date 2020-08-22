package com.example.demo.data;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

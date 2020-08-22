package com.example.demo.service;

import com.example.demo.data.CategoryRepository;
import com.example.demo.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(String categoryName, BigDecimal discount) {
        Category category = new Category(categoryName, discount);
        return categoryRepository.save(category);
    }
}

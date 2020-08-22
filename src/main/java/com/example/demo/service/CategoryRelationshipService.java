package com.example.demo.service;

import com.example.demo.data.CategoryRelationshipRepository;
import com.example.demo.models.CategoryRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRelationshipService {

    @Autowired
    private CategoryRelationshipRepository categoryRelationshipRepository;

    public CategoryRelationship create(Long categoryId, Long parentCategoryId) {
        CategoryRelationship categoryRelationship = new CategoryRelationship(categoryId, parentCategoryId);
        return categoryRelationshipRepository.save(categoryRelationship);
    }
}

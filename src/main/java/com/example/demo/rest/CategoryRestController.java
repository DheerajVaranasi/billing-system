package com.example.demo.rest;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryRelationship;
import com.example.demo.service.CategoryRelationshipService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRelationshipService categoryRelationshipService;

    @RequestMapping(method = RequestMethod.POST, path = "/category")
    public Category create(@RequestBody Request request) {
        return categoryService.create(request.getName(), request.getDiscount());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/relationship")
    public CategoryRelationship createCategoryRelationship(@RequestBody Request request) {
        return categoryRelationshipService.create(request.getCategoryId(), request.getParentCategoryId());
    }

    private static class Request {
        private String name;
        private BigDecimal discount;
        private Long categoryId;
        private Long parentCategoryId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public Long getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(Long parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }
    }
}

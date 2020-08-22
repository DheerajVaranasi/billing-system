package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category_relationships")
public class CategoryRelationship extends com.example.demo.models.entity.Entity {

    @NotNull
    private Long categoryId;

    @NotNull
    private Long parentCategoryId;

    public CategoryRelationship(Long categoryId, Long parentCategoryId) {
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
    }

    public CategoryRelationship() {

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

    @Override
    public String toString() {
        return "CategoryRelationship{" +
                "categoryId=" + categoryId +
                '}';
    }
}

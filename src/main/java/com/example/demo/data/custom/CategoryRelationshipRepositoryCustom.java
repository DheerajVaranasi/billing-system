package com.example.demo.data.custom;

import com.example.demo.models.CategoryRelationship;

import java.util.Collection;
import java.util.List;

public interface CategoryRelationshipRepositoryCustom {

    List<CategoryRelationship> findByCategoryIdIn(Collection<Long> categoryIds);
}

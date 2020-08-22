package com.example.demo.data;

import com.example.demo.data.custom.CategoryRelationshipRepositoryCustom;
import com.example.demo.models.CategoryRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRelationshipRepository extends JpaRepository<CategoryRelationship, Long>, CategoryRelationshipRepositoryCustom {
}

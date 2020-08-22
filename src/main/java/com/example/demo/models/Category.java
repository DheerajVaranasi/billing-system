package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "categories")
public class Category extends com.example.demo.models.entity.Entity {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private BigDecimal discount;

    public Category(String name, BigDecimal discount) {
        this.name = name;
        this.discount = discount;
    }

    public Category() {

    }

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

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}

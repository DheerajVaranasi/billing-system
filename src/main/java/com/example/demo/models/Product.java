package com.example.demo.models;

import com.example.demo.models.entity.Entity;
import com.example.demo.models.enums.DiscountEnum;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "products")
public class Product extends Entity {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Long categoryId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private DiscountEnum discountEnum; // 10% off - PERCENTAGE or Buy 2 get 1 - EXTRAITEMS

    @NotNull
    private BigDecimal discount; // 10 or 1

    @NotNull
    private Integer items; // 2

    public Product(String name, Long categoryId, BigDecimal price, DiscountEnum discountEnum, BigDecimal discount, Integer items) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.discountEnum = discountEnum;
        this.discount = discount;
        this.items = items;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DiscountEnum getDiscountEnum() {
        return discountEnum;
    }

    public void setDiscountEnum(DiscountEnum discountEnum) {
        this.discountEnum = discountEnum;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}

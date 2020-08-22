package com.example.demo.models.util;

import com.example.demo.models.enums.DiscountEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCart {
    private Long item;
    private Long quantity; // 8
    @JsonIgnore
    private BigDecimal price; // 20 160
    @JsonIgnore
    private DiscountEnum discountEnum;
    @JsonIgnore
    private BigDecimal discount; // 1
    @JsonIgnore
    private Integer items; // 3 (8 / 3) = 2 8 - 2 = 6  1/3== 33
    private BigDecimal discountedPrice;

    public ShoppingCart(Long item, Long quantity, BigDecimal price, DiscountEnum discountEnum, BigDecimal discount, Integer items) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.discountEnum = discountEnum;
        this.discount = discount;
        this.items = items;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public void generate() {
        if (discountEnum != null) {
            if (discountEnum.equals(DiscountEnum.PERCENTAGE)) {
                BigDecimal bigDecimal = price.multiply(BigDecimal.valueOf(quantity)).multiply(discount).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_EVEN);
                this.setDiscountedPrice(bigDecimal);
            } else if (discountEnum.equals(DiscountEnum.EXTRAITEMS)) {
                BigDecimal fullPrice = price.multiply(BigDecimal.valueOf(quantity));
                // Subtract discount price from full price
                this.setDiscountedPrice(fullPrice);
            }
        }
    }
}

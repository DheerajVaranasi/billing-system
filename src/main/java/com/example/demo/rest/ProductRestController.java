package com.example.demo.rest;

import com.example.demo.models.Product;
import com.example.demo.models.enums.DiscountEnum;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST, path = "/product")
    public Product create(@RequestBody Request request) {
        Product product = new Product(request.getName(), request.getCategoryId(), request.getPrice(), request.getDiscountEnum(), request.getDiscount(), request.getItems());
        return productService.create(product);
    }

    private static class Request {
        private String name;
        private Long categoryId;
        private BigDecimal price;
        private DiscountEnum discountEnum;
        private BigDecimal discount;
        private Integer items;

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
    }
}

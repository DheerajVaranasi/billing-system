package com.example.demo.service;

import com.example.demo.data.CategoryRelationshipRepository;
import com.example.demo.data.CategoryRepository;
import com.example.demo.data.ProductRepository;
import com.example.demo.models.Category;
import com.example.demo.models.CategoryRelationship;
import com.example.demo.models.Product;
import com.example.demo.models.entity.Entity;
import com.example.demo.models.enums.DiscountEnum;
import com.example.demo.models.util.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class InvoiceService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRelationshipRepository categoryRelationshipRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ShoppingCart> invoice(List<ShoppingCart> shoppingCarts) {
        List<Long> ids = shoppingCarts.stream().map(ShoppingCart::getItem).collect(Collectors.toList());
        Map<Long, Product> foundProducts = mapProducts(productRepository.findAllById(ids));
        Map<Long, Product> discountPercentProductCategoryIds = foundProducts.values().stream().filter(p -> p.getDiscountEnum().equals(DiscountEnum.PERCENTAGE)).collect(Collectors.toMap(Entity::getId, Function.identity()));
        List<ShoppingCart> shoppingCartWithDiscounts = calPercentageDiscounts(shoppingCarts, discountPercentProductCategoryIds);
        Map<Long, Product> productMap = foundProducts.values().stream().filter(p -> p.getDiscountEnum().equals(DiscountEnum.EXTRAITEMS)).collect(Collectors.toMap(Entity::getId, Function.identity()));
        List<ShoppingCart> finalShoppingCart = calExtraItemsDiscounts(shoppingCartWithDiscounts, productMap);
        finalShoppingCart.forEach(ShoppingCart::generate);
        return finalShoppingCart;
    }

    private Map<Long, Product> mapProducts(List<Product> products) {
        return products.stream().collect(Collectors.toMap(Entity::getId, Function.identity()));
    }

    private List<ShoppingCart> calPercentageDiscounts(List<ShoppingCart> shoppingCarts, Map<Long, Product> discountPercentProductCategoryIds) {
        Map<Long, CategoryRelationship> categoryRelationshipMap = categoryRelationshipRepository.findByCategoryIdIn(discountPercentProductCategoryIds.keySet()).stream().collect(Collectors.toMap(Entity::getId, Function.identity()));
        Map<Long, Category> subCategories = categoryRepository.findAllById(categoryRelationshipMap.keySet()).stream().collect(Collectors.toMap(Entity::getId, Function.identity()));
        List<Long> parentCategoryIds = categoryRelationshipMap.values().stream().map(CategoryRelationship::getParentCategoryId).collect(Collectors.toList());
        Map<Long, Category> parentCategories = categoryRepository.findAllById(parentCategoryIds).stream().collect(Collectors.toMap(Entity::getId, Function.identity()));
        return shoppingCarts.stream().peek(sc -> {
            Product product = discountPercentProductCategoryIds.get(sc.getItem());
            if (product != null) {
                sc.setDiscount(product.getDiscount());
                sc.setPrice(product.getPrice());
                sc.setItems(product.getItems());
                Category productCategory = subCategories.get(product.getCategoryId());
                if (productCategory != null) {
                    Category productCategoryParent = parentCategories.get(productCategory.getId());
                    if (productCategory.getDiscount().compareTo(sc.getDiscount()) > 0) {
                        sc.setDiscount(productCategory.getDiscount());
                    }
                    if (productCategoryParent.getDiscount().compareTo(sc.getDiscount()) > 0) {
                        sc.setDiscount(productCategoryParent.getDiscount());
                    }
                }
                sc.setDiscountEnum(DiscountEnum.PERCENTAGE);
            }
        }).collect(Collectors.toList());
    }

    private List<ShoppingCart> calExtraItemsDiscounts(List<ShoppingCart> shoppingCartsWithDiscounts, Map<Long, Product> discountExtraItemsProductMap) {
        return shoppingCartsWithDiscounts.stream().peek(sc -> {
            Product foundProduct = discountExtraItemsProductMap.get(sc.getItem());
            if (foundProduct != null) {
                sc.setDiscountEnum(DiscountEnum.EXTRAITEMS);
                sc.setPrice(foundProduct.getPrice());
                sc.setDiscount(foundProduct.getDiscount());
                sc.setItems(foundProduct.getItems());
            }
        }).collect(Collectors.toList());
    }
}

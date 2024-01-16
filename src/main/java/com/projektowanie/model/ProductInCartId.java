package com.projektowanie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductInCartId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    public ProductInCartId() {

    }

    public ProductInCartId(Long productId, Long shoppingCartId) {
        this.productId = productId;
        this.shoppingCartId = shoppingCartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInCartId that = (ProductInCartId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(shoppingCartId, that.shoppingCartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, shoppingCartId);
    }
}

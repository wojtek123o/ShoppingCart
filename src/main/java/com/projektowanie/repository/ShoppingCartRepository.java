package com.projektowanie.repository;

import com.projektowanie.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc LEFT JOIN FETCH sc.productsInCart WHERE sc.shoppingCartId = :id")
    Optional<ShoppingCart> findShoppingCartWithProductsById(@Param("id") Long id);

}

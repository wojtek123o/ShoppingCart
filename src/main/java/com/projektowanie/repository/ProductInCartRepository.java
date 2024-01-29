package com.projektowanie.repository;

import com.projektowanie.model.ProductInCart;
import com.projektowanie.model.ProductInCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, ProductInCartId> {

}

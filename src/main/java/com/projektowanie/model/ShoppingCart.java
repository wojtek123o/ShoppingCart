package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartId;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "shoppingCart")
    private List<ProductInCart> productsInCart;

    @OneToOne(mappedBy = "shoppingCart")
    private Client client;

    @OneToOne(mappedBy = "shoppingCart")
    private CustomerOrder customerOrder;
}

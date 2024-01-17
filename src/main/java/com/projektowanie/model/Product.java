package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private BigDecimal price;

    private byte[] image;

    private String description;

    @ManyToOne
    @JoinColumn (name = "catalogOfProductId")
    private Catalog catalog;

    @ManyToOne
    @JoinColumn (name = "categoryOfProductId")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductInCart> productsInCart;
}

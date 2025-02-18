package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private String imageName;

    private String description;

    @ManyToOne
    @JoinColumn (name = "catalog_of_product_id")
    private Catalog catalog;

    @ManyToOne
    @JoinColumn (name = "category_of_product_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductInCart> productsInCart;
}

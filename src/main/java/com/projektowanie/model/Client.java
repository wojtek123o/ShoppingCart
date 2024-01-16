package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String streetName;

    private String postalCode;

    private String nip;

    @OneToMany(mappedBy = "client")
    private List<Discount> discounts;

    @OneToOne()
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shoppingCartId")
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "client")
    private List<CustomerOrder> customerOrders;
}

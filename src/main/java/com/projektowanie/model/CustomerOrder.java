package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Timestamp orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Timestamp shippingDate;

    private BigDecimal orderTotalPrice;

    @ManyToOne
    @JoinColumn (name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn (name = "payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn (name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn (name = "shipping_id")
    private Shipping shipping;

    @OneToOne()
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shoppingCartId")
    private ShoppingCart shoppingCart;

    @OneToOne(mappedBy = "customerOrder")
    private Complaint complaint;
}


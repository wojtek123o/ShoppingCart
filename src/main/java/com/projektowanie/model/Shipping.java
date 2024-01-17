package com.projektowanie.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    private Timestamp sendingDate;

    private Timestamp deliveryDate;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    @Enumerated(EnumType.STRING)
    private ShippingType shippingType;

    private BigDecimal shippingPrice;

    @OneToMany (mappedBy = "shipping")
    private List<CustomerOrder> customerOrders;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;
}

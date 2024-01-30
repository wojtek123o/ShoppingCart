package com.projektowanie.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    private String shippingCity;

    private String shippingStreet;

    private String shippingStreetNumber;

    private String shippingHouseNumber;

    private String shippingPostalCode;

    @OneToMany (mappedBy = "shipping")
    private List<CustomerOrder> customerOrders = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;
}

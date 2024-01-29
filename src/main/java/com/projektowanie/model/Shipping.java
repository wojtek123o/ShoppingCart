package com.projektowanie.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
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

    @NotBlank(message = "Miasto jest wymagane")
    private String shippingCity;
    @NotBlank(message = "Ulica jest wymagana")
    private String shippingStreet;
    @Positive(message = "Wpisz poprawny numer ulicy")
    private Integer shippingStreetNumber;
    @Positive(message = "Wpisz poprawny numer mieszkania")
    private Integer shippingHouseNumber;
    @Positive(message = "Wpisz poprawny kod pocztowy")
    private Integer shippingPostalCode;

    @OneToMany (mappedBy = "shipping")
    private List<CustomerOrder> customerOrders = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;
}

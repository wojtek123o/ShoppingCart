package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    private BigDecimal discountAmount;

    private Timestamp issueDate;

    private Timestamp expirationDate;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean isUsed;

    @ManyToOne
    @JoinColumn (name = "discount_for_client_id")
    private Client client;

    @OneToMany (mappedBy = "discount")
    private List<CustomerOrder> customerOrders;
}

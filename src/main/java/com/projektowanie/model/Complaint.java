package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    private String reason;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus;

    private Timestamp complaintDate;

    @OneToOne()
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}

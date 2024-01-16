package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId;

    private Timestamp lastModificationDate;

    private String name;

    @OneToMany (mappedBy = "catalog")
    private List<Product> products;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;
}

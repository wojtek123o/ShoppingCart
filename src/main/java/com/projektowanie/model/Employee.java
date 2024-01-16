package com.projektowanie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String login;

    private String password;

    private Timestamp employmentDate;

    private String PESEL;

    private String employeeNumber;

    private String position;

    @OneToMany(mappedBy = "employee")
    private List<Complaint> complaints;

    @OneToMany (mappedBy = "employee")
    private List<Shipping> shippings;

    @OneToMany (mappedBy = "employee")
    private List<Catalog> catalogs;
}

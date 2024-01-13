package com.projektowanie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Catalog {

    @Id
    private Long id;

    private Timestamp lastModificationDate;

    private String name;

    @OneToMany
    private List<Product> products;
}

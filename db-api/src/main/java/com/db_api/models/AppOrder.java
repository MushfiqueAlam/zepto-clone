package com.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class AppOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    LocalDateTime placedTime;

    @ManyToOne
    AppUser customer;

    @ManyToOne
    AppUser deliveryPartner;
    double totalAmount;

    @ManyToMany
    List<Product> products;
}

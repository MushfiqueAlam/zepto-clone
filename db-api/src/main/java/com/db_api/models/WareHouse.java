package com.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String address;
    String email;
    int pincode;

    @OneToOne
    AppUser manager;
}

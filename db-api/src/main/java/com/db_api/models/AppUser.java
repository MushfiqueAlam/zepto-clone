package com.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    @Column(unique = true)
    String email;
    @Column(nullable = false)
    String password;
    @Column(unique = true)
    Long phoneNumber;
    String userType;
    int pincode;
    String address;
    String status;

}

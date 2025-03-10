package com.central_api.models;

import lombok.Data;

import java.util.UUID;
@Data
public class WareHouse {
    UUID id;
    String name;
    String email;
    String address;
    int pincode;
    AppUser manager;
}

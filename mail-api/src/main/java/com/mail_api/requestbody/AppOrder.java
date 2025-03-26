package com.mail_api.requestbody;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AppOrder {
    UUID id;
    LocalDateTime placedTime;
    AppUser customer;
    AppUser deliveryPartner;
    double totalAmount;
    List<Product>products;
}

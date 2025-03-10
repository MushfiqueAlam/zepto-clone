package com.central_api.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AppOrder {
    UUID id;
    LocalDateTime placedTime;
    AppUser customer;
    AppUser deliveryPartner;
    double totalAmount;
    List<Product> products;
}

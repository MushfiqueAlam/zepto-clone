package com.central_api.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppOrder {
    UUID id;
    LocalDateTime placedTime;
    AppUser customer;
    AppUser deliveryPartner;
    double totalAmount;
    List<Product> products;
}

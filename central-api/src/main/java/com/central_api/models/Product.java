package com.central_api.models;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    UUID id;
    String productName;
    int productPrice;
    String details;
    String manufacturerEmail;
    double rating;
    int weight;
    int totalPurchase;
}

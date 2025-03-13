package com.central_api.requestDto;

import lombok.Data;

@Data
public class RegisterProductDto {
    String productName;
    int productPrice;
    String details;
    String manufacturerEmail;
    double rating;
    int weight;
    int totalPurchase;
}

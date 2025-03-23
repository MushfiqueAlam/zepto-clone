package com.central_api.responseDto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseBillProductDto {
    UUID productId;
    String productName;
    int quantity;
    double amount;
}

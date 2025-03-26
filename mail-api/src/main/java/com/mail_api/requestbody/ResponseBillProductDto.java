package com.mail_api.requestbody;

import lombok.Data;

import java.util.UUID;
@Data
public class ResponseBillProductDto {
    UUID productId;
    String productName;
    int quantity;
    double amount;
}

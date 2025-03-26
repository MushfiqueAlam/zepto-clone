package com.mail_api.requestbody;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ResponseBillDto {
    UUID orderId;
    LocalDateTime orderPlacedTime;
    List<ResponseBillProductDto> products;
    double totalBillPayed;
}

package com.central_api.responseDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ResponseBillDto {
    UUID orderId;
    LocalDateTime orderPlacedTime;
    List<ResponseBillProductDto> product;
    double totalBilledPayed;
}

package com.central_api.requestDto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterWareHouseProductDto {
    UUID wid;
    UUID pid;
    int discount;
    int totalQuantity;
}

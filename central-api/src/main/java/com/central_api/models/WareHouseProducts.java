package com.central_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseProducts {
    UUID id;
    UUID wid;
    UUID pid;
    int discount;
    int totalQuantity;
}

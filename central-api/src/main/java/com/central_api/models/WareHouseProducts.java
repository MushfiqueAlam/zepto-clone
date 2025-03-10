package com.central_api.models;

import lombok.Data;

import java.util.UUID;

@Data
public class WareHouseProducts {
    UUID id;
    UUID wid;
    UUID pid;
    int discount;
    int totalQuantity;
}

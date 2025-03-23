package com.central_api.requestDto;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestOrderProductDto {
    UUID pid;
    int quantity;
}

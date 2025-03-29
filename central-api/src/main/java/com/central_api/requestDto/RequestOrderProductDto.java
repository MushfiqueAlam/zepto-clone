package com.central_api.requestDto;

import lombok.*;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestOrderProductDto {
    UUID pid;
    int quantity;
}

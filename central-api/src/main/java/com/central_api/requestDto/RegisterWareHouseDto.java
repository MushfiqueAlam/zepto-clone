package com.central_api.requestDto;

import com.central_api.models.AppUser;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterWareHouseDto {
    String name;
    String email;
    String address;
    int pinCode;
    UUID managerId;
}

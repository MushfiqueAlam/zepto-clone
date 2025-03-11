package com.central_api.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AppUser {
    UUID id;
    String name;
    String email;
    String password;
    Long phoneNumber;
    String userType;
    int pinCode;
    String address;
    String status;
}

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

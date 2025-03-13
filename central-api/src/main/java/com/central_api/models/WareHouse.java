package com.central_api.models;

import lombok.*;

import java.security.cert.CertPathBuilder;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WareHouse {
    UUID id;
    String name;
    String email;
    String address;
    int pinCode;
    AppUser manager;


}

package com.mail_api.requestbody;

import lombok.Data;

import java.util.UUID;
@Data
public class Product {
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

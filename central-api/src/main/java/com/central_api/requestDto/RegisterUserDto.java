package com.central_api.requestDto;

import com.central_api.enums.UserType;
import lombok.Data;

@Data
public class RegisterUserDto {
    String name;
    String email;
    String password;
    Long phoneNumber;
    int pinCode;
    String address;
    UserType userType;

}

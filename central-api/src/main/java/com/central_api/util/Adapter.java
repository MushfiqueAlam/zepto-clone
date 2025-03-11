package com.central_api.util;

import com.central_api.models.AppUser;
import com.central_api.requestDto.RegisterUserDto;
import org.springframework.stereotype.Component;

@Component
public class Adapter {
    public AppUser requestDtoToAppUser(RegisterUserDto userDto){
        AppUser appUser=AppUser.builder().name(userDto.getName()).email(userDto.getEmail())
                .userType(userDto.getUserType().toString())
                .address(userDto.getAddress())
                .pinCode(userDto.getPinCode())
                .status("ACTIVE")
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

        return appUser;
    }
}

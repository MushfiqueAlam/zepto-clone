package com.central_api.service;

import com.central_api.models.AppUser;
import com.central_api.requestDto.RegisterUserDto;
import com.central_api.util.Adapter;
import com.central_api.util.DatabaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    Adapter adapter;

    @Autowired
    DatabaseApi databaseApi;
    public AppUser createUser(RegisterUserDto userDto) {
        AppUser appUser=adapter.requestDtoToAppUser(userDto);
        AppUser response=databaseApi.callCreateUserEndPoint(appUser);
        return  response;
    }

    public List<AppUser> getDeliveryPartnerByPincode(int pinCode){
        return databaseApi.getDeliveryPartnerByPinCode(pinCode);
    }
}

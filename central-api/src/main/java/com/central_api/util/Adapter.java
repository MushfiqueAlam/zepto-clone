package com.central_api.util;

import com.central_api.models.AppUser;
import com.central_api.models.Product;
import com.central_api.models.WareHouse;
import com.central_api.requestDto.RegisterProductDto;
import com.central_api.requestDto.RegisterUserDto;
import com.central_api.requestDto.RegisterWareHouseDto;
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

    public Product requestDtoToProduct(RegisterProductDto registerProductDto){
        Product product=Product.builder()
                .productName(registerProductDto.getProductName())
                .productPrice(registerProductDto.getProductPrice())
                .manufacturerEmail(registerProductDto.getManufacturerEmail())
                .details(registerProductDto.getDetails())
                .weight(registerProductDto.getWeight())
                .rating(registerProductDto.getRating())
                .totalPurchase(registerProductDto.getTotalPurchase())
                .build();
        return product;
    }

    public WareHouse requestDtoToWareHouse(RegisterWareHouseDto wareHouseDto,AppUser manager){
        WareHouse wareHouse=WareHouse.builder()
                .name(wareHouseDto.getName())
                .pinCode(wareHouseDto.getPinCode())
                .address(wareHouseDto.getAddress())
                .email(wareHouseDto.getEmail())
                .manager(manager)
                .build();
        return wareHouse;
    }

}

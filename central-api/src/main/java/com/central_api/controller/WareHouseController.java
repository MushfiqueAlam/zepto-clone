package com.central_api.controller;

import com.central_api.exception.UnAuthorized;
import com.central_api.models.WareHouse;
import com.central_api.models.WareHouseProducts;
import com.central_api.requestDto.RegisterWareHouseDto;
import com.central_api.requestDto.RegisterWareHouseProductDto;
import com.central_api.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/warehouse")
public class WareHouseController {
    @Autowired
    WareHouseService wareHouseService;

    @PostMapping("/create")
    public WareHouse createWareHouse(@RequestParam UUID userId, @RequestBody RegisterWareHouseDto wareHouseDto) throws UnAuthorized {
        WareHouse wareHouse=wareHouseService.createWareHouse(userId,wareHouseDto);
        return wareHouse;
    }

    @PostMapping("/addProduct")
    public WareHouseProducts addProductsToWareHouse(@RequestParam UUID userId, @RequestBody RegisterWareHouseProductDto productDto) throws UnAuthorized {
        WareHouseProducts products=wareHouseService.addProductsToWareHouse(userId,productDto);
        return products;
    }
}

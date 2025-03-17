package com.db_api.controller;

import com.db_api.models.WareHouseProducts;
import com.db_api.repository.WareHouseProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/warehouse/products")
public class WareHouseProductsController {

    @Autowired
    WareHouseProductsRepository productsRepository;

    @PostMapping("/save")
    public WareHouseProducts addProduct(@RequestBody WareHouseProducts products){
        WareHouseProducts products1=productsRepository.save(products);
        return products1;
    }
}

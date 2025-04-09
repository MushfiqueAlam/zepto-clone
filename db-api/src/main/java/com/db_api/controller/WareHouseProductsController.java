package com.db_api.controller;

import com.db_api.models.WareHouseProducts;
import com.db_api.repository.WareHouseProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/warehouse/product")
public class WareHouseProductsController {

    @Autowired
    WareHouseProductsRepository productsRepository;

    @PostMapping("/save")
    public WareHouseProducts addProduct(@RequestBody WareHouseProducts products){
        WareHouseProducts products1=productsRepository.save(products);
        return products1;
    }

    @GetMapping("/{wid}")
    public List<WareHouseProducts> getAllProductsByWid(@PathVariable UUID wid){
        return productsRepository.getWareHouseProductsByWid(wid);
    }

    @GetMapping("/{wid}/{pid}")
    public WareHouseProducts getProductByWidPid(@PathVariable UUID wid,@PathVariable UUID pid ){
        return productsRepository.getProductByWidPid(wid,pid);
    }
}

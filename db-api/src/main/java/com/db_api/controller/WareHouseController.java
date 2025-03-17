package com.db_api.controller;

import com.db_api.models.WareHouse;
import com.db_api.repository.WareHoseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/warehouse")
public class WareHouseController {

    WareHoseRepository wareHoseRepository;
    public WareHouseController(WareHoseRepository wareHoseRepository){
        this.wareHoseRepository=wareHoseRepository;
    }
    @PostMapping("/save")
    public WareHouse createWareHouse(@RequestBody WareHouse wareHouse){
        WareHouse wareHouse1=wareHoseRepository.save(wareHouse);
        return wareHouse1;
    }
}

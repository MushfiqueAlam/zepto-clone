package com.db_api.controller;

import com.db_api.models.WareHouse;
import com.db_api.repository.WareHoseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{wareHouseId}")
    public WareHouse getWareHouseById(@PathVariable UUID wareHouseId){
        return wareHoseRepository.findById(wareHouseId).orElse(null);
    }

    @PutMapping("/update")
    public WareHouse updateWareHouse(@RequestBody WareHouse wareHouse){
       wareHoseRepository.save(wareHouse);
       return wareHouse;
    }

    @GetMapping("/pinCode/{pinCode}")
    public WareHouse getWareHouseUsingPinCode(@PathVariable int pinCode){
        return wareHoseRepository.getWareHouseByPinCode(pinCode);
    }
}

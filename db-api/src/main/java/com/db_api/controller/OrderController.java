package com.db_api.controller;

import com.db_api.models.AppOrder;
import com.db_api.repository.AppOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/order/")
public class OrderController {
    @Autowired
    AppOrderRepository orderRepository;

    @PostMapping("/save")
    public AppOrder createOrder(@RequestBody AppOrder order){
        return orderRepository.save(order);
    }


}

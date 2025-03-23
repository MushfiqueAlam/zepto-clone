package com.central_api.controller;

import com.central_api.exception.ProductNotPresentException;
import com.central_api.exception.WareHouseNotFoundException;
import com.central_api.models.WareHouseProducts;
import com.central_api.requestDto.RequestOrderProductDto;
import com.central_api.responseDto.ResponseBillDto;
import com.central_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    public ResponseBillDto placeOrder(@RequestBody List<RequestOrderProductDto> products, @RequestParam UUID userId) throws ProductNotPresentException, WareHouseNotFoundException {
        return orderService.placeOrder(products,userId);
    }
}

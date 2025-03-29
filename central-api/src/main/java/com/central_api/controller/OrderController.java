package com.central_api.controller;

import com.central_api.exception.ProductNotPresentException;
import com.central_api.exception.UserNotFoundException;
import com.central_api.exception.WareHouseNotFoundException;
import com.central_api.models.WareHouseProducts;
import com.central_api.requestDto.RequestOrderProductDto;
import com.central_api.responseDto.ResponseBillDto;
import com.central_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody List<RequestOrderProductDto> products, @RequestParam UUID userId) throws ProductNotPresentException, WareHouseNotFoundException {
        try{
            ResponseBillDto bill=orderService.placeOrder(products,userId);
            return new ResponseEntity<>(bill, HttpStatus.CREATED);
        }catch (ProductNotPresentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (WareHouseNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accept/{partnerId}/{orderId}")
    public void  acceptOrder(@PathVariable UUID partnerId,@PathVariable UUID orderId){
        orderService.acceptOrder(orderId,partnerId);
    }
}

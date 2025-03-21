package com.central_api.controller;

import com.central_api.exception.UserNotFoundException;
import com.central_api.exception.WareHouseNotFoundException;
import com.central_api.models.AppUser;
import com.central_api.models.Product;
import com.central_api.requestDto.RegisterUserDto;
import com.central_api.service.UserService;
import com.central_api.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/user")
public class UserController {
        UserService userService;
        @Autowired
        public UserController(UserService userService){
            this.userService=userService;
        }

        @Autowired
    WareHouseService wareHouseService;

        @PostMapping("/register")
        public AppUser createUser(@RequestBody RegisterUserDto userDto){
            AppUser appUser=userService.createUser(userDto);
            return appUser;
        }

        @GetMapping("/products")

        public ResponseEntity<?> getProductByPinCode(@RequestParam UUID userId){
            try{
                List<Product> products=wareHouseService.getWareHouseProduct(userId);
                return new ResponseEntity<>(products, HttpStatus.OK);
            } catch (WareHouseNotFoundException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            }catch(UserNotFoundException u){
                return new ResponseEntity<>(u.getMessage(),HttpStatus.NOT_FOUND);
            }
        }

        
}

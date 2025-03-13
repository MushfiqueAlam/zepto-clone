package com.central_api.controller;

import com.central_api.models.AppUser;
import com.central_api.requestDto.RegisterUserDto;
import com.central_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/user")
public class UserController {
        UserService userService;
        @Autowired
        public UserController(UserService userService){
            this.userService=userService;
        }

        @PostMapping("/register")
        public AppUser createUser(@RequestBody RegisterUserDto userDto){
            AppUser appUser=userService.createUser(userDto);
            return appUser;
        }

        
}

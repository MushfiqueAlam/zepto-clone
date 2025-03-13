package com.db_api.controller;

import com.db_api.models.AppOrder;
import com.db_api.models.AppUser;
import com.db_api.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private AppUserRepository appUserRepository;
    @Autowired
    public AppUserController(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser){
        if (appUser .getPinCode() == 0) {
            return ResponseEntity.badRequest().body(null);
        }
        AppUser  savedUser  = appUserRepository.save(appUser );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser );
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<AppUser> getUserById(@PathVariable UUID userId){
//        AppUser appUser=appUserRepository.findById(userId).get();
//        if(appUser!=null){
//            return ResponseEntity.ok(appUser);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppUser> updateUserById(@RequestBody AppUser appUser,@PathVariable UUID id){
        AppUser appUser1=appUserRepository.findById(id).get();
        if(appUser1==null){
            return ResponseEntity.notFound().build();
        }
        appUser1.setName(appUser.getName());
        appUser1.setEmail(appUser.getEmail());
        appUser1.setAddress(appUser.getAddress());
        appUser1.setUserType(appUser.getUserType());
        appUser1.setPassword(appUser.getPassword());
        appUser1.setPhoneNumber(appUser.getPhoneNumber());
        appUser1.setStatus(appUser.getStatus());
        appUser1.setAddress(appUser.getAddress());
        appUser1.setPinCode(appUser.getPinCode());
       AppUser appUser2=  appUserRepository.save(appUser1);
        return ResponseEntity.status(HttpStatus.OK).body(appUser2);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id){
        appUserRepository.deleteById(id);
    }

    @GetMapping("/getByPinCode/{pinCode}")
    public ResponseEntity<List<AppUser>> getByPinCode(@PathVariable int pinCode){
       List<AppUser> user= (List<AppUser>) appUserRepository.findByPinCode(pinCode);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/findByEmail/{email}")
    public AppUser getByEmail(@PathVariable String email){
        return appUserRepository.findByEmail(email);
    }
    @GetMapping("/{email}")
    public ResponseEntity<AppUser> getUserByEmail(@PathVariable String email){
        logger.info(String.valueOf(email));
        AppUser appUser=appUserRepository.findByEmail(email);
            logger.info(String.valueOf(appUser));
        if(appUser!=null){
            return ResponseEntity.ok(appUser);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

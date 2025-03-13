package com.central_api.util;

import com.central_api.enums.UserType;
import com.central_api.exception.UserNotFoundException;
import com.central_api.models.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    public boolean isZeptoApplicationAdmin(AppUser user){
        if(user==null){
            throw  new UserNotFoundException(String.format("User is not exit!"));
        }
        return user.getUserType().equals(UserType.APPLICATION_ADMIN.toString())?true:false;
    }

    public boolean isZeptoApplicationManager(AppUser user){
        if(user==null){
            throw new UserNotFoundException(String.format("User is not exit!"));
        }
        return user.getUserType().equals(UserType.WAREHOUSE_MANAGER.toString())?true:false;
    }
}

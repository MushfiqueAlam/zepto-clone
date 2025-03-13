package com.central_api.service;

import com.central_api.enums.UserType;
import com.central_api.exception.UnAuthorized;
import com.central_api.exception.UserNotFoundException;
import com.central_api.models.AppUser;
import com.central_api.models.Product;
import com.central_api.requestDto.RegisterProductDto;
import com.central_api.util.Adapter;
import com.central_api.util.DatabaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    Adapter adapter;

    @Autowired
    DatabaseApi databaseApi;

    public Product createProduct(RegisterProductDto product, String email) throws UserNotFoundException, UnAuthorized {
        Product product1=adapter.requestDtoToProduct(product);
        AppUser user=databaseApi.getUserByEmail(email);
        if(user==null){
            throw new UserNotFoundException(String.format("User not found with this %s email",email));
        }

        if(!user.getUserType().equals(UserType.APPLICATION_ADMIN.toString())){
            throw new UnAuthorized(String.format("User with email %s does not have to access to create product",email));
        }
        Product respProduct=databaseApi.callCreateProductEndPoint(product1);
        return respProduct;
    }
}

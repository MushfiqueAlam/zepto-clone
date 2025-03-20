package com.central_api.service;

import com.central_api.enums.UserType;
import com.central_api.exception.UnAuthorized;
import com.central_api.exception.UserNotFoundException;
import com.central_api.exception.WareHouseNotFoundException;
import com.central_api.models.AppUser;
import com.central_api.models.Product;
import com.central_api.models.WareHouse;
import com.central_api.models.WareHouseProducts;
import com.central_api.requestDto.RegisterWareHouseDto;
import com.central_api.requestDto.RegisterWareHouseProductDto;
import com.central_api.util.Adapter;
import com.central_api.util.DatabaseApi;
import com.central_api.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WareHouseService {
    @Autowired
    DatabaseApi databaseApi;

    @Autowired
    Adapter adapter;

    @Autowired
    UserUtil userUtil;

    public WareHouse createWareHouse(UUID id,RegisterWareHouseDto wareHouseDto) throws UnAuthorized {
        AppUser user=databaseApi.getUserByUserId(id);

        if(user==null){
            throw new UserNotFoundException(String.format("User is not exit with this %s id",id.toString()));
        }
        if(!user.getUserType().equals(UserType.WAREHOUSE_MANAGER.toString())){
            throw  new UnAuthorized(String.format("User with this %s id is not access",id.toString()));
        }

        UUID managerId=wareHouseDto.getManagerId();
        AppUser manager=databaseApi.getUserByUserId(managerId);
        WareHouse wareHouse=adapter.requestDtoToWareHouse(wareHouseDto,manager);

        WareHouse wareHouseResponse=databaseApi.createWareHouse(wareHouse);
        return wareHouseResponse;
    }

    public WareHouseProducts addProductsToWareHouse(UUID userId, RegisterWareHouseProductDto productDto) throws UnAuthorized {
        AppUser user=databaseApi.getUserByUserId(userId);

        if(!userUtil.isZeptoApplicationAdmin(user) && !userUtil.isZeptoApplicationManager(user)){
            throw  new UnAuthorized(String.format("user with %s userId does not have to access to add products in wareHouse",userId.toString()));
        }

        WareHouseProducts products=adapter.requestDtoToWareHouseProduct(productDto);
        WareHouseProducts wareHouseProductsResponse=databaseApi.createWareHouseProducts(products);
        return wareHouseProductsResponse;
    }

    public Product getProductById(UUID pId){
        return databaseApi.getProductByProductId(pId);
    }

    public List<WareHouseProducts> getWareHouseProductByWareHouseId(UUID wId){
        return databaseApi.getProductByWareHouseId(wId);
    }

    public List<Product> getWareHouseProduct(UUID userId) throws WareHouseNotFoundException {
        AppUser user=databaseApi.getUserByUserId(userId);
        if(user==null){
            throw new UserNotFoundException(String.format("user with id %s does not exit in system",userId.toString()));
        }
        int pinCode= user.getPinCode();
        WareHouse wareHouse=databaseApi.getWareHouseByPinCode(pinCode);
        if(wareHouse==null){
            throw new WareHouseNotFoundException(String.format("We regret that we don't provide any service in your region"));

        }
        List<WareHouseProducts>wareHouseProducts=this.getWareHouseProductByWareHouseId(wareHouse.getId());
        List<Product>products=new ArrayList<>();
        for(WareHouseProducts wp:wareHouseProducts){
            UUID pId=wp.getId();
            Product p=this.getProductById(pId);
            products.add(p);

        }
        return products;

    }
}

package com.central_api.service;

import com.central_api.exception.ProductNotPresentException;
import com.central_api.exception.UserNotFoundException;
import com.central_api.exception.WareHouseNotFoundException;
import com.central_api.models.*;
import com.central_api.requestDto.RequestOrderDto;
import com.central_api.requestDto.RequestOrderProductDto;
import com.central_api.responseDto.ResponseBillDto;
import com.central_api.responseDto.ResponseBillProductDto;
import com.central_api.util.DatabaseApi;
import com.central_api.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    DatabaseApi databaseApi;

    @Autowired
    WareHouseService wareHouseService;

    @Autowired
    MailUtil mailUtil;

    public double getPriceAfterDiscount(int amount,int discount){
        double offAmount=amount*(discount/100);
        return amount-offAmount;
    }


    public void notifyDeliveryPartner(int pinCode,AppUser customer,ResponseBillDto bill){
        List<AppUser> dp=databaseApi.getDeliveryPartnerByPinCode(pinCode);

        for(AppUser partner:dp){
            RequestOrderDto order=new RequestOrderDto();
            order.setCustomer(customer);
            order.setBill(bill);
            order.setDeliveryPartner(partner);
            mailUtil.sendNotification(order);
        }
    }


    public ResponseBillDto placeOrder(List<RequestOrderProductDto> products, UUID userId) throws WareHouseNotFoundException, ProductNotPresentException {
        AppUser user=databaseApi.getUserByUserId(userId);
        if(user==null){
            throw new UserNotFoundException(String.format("User not found with this %s userId ",userId.toString()));
        }

        int pinCode= user.getPinCode();
        WareHouse wareHouse=databaseApi.getWareHouseByPinCode(pinCode);
        if(wareHouse==null){
            throw new WareHouseNotFoundException(String.format("We regret to inform you warehouse is not present in this %d pinCode",pinCode));
        }

        ResponseBillDto bill=new ResponseBillDto();
        List<ResponseBillProductDto> billProduct=new ArrayList<>();
        AppOrder order=new AppOrder();
        List<Product>orderProdeuctList=new ArrayList<>();

        double totalAmount=0.0;

        for(RequestOrderProductDto product:products){
            UUID pid=product.getPid();

            WareHouseProducts wareHouseProducts=wareHouseService.getProductByWidPid(wareHouse.getId(),pid);
            if(wareHouseProducts.getTotalQuantity()<product.getQuantity()){
                throw new ProductNotPresentException(String.format("Product with productId %s does not have enough quantity ",pid.toString()));
            }

            Product presentProduct=wareHouseService.getProductById(pid);
            orderProdeuctList.add(presentProduct);
            ResponseBillProductDto billProductDto=new ResponseBillProductDto();
            billProductDto.setProductId(pid);
            billProductDto.setQuantity(product.getQuantity());

            double priceAfterDiscount=this.getPriceAfterDiscount(presentProduct.getProductPrice(),wareHouseProducts.getDiscount());
            billProductDto.setAmount(product.getQuantity()*priceAfterDiscount);
            billProductDto.setProductName(presentProduct.getProductName());
            billProduct.add(billProductDto);
            totalAmount+=priceAfterDiscount;
        }
        order.setCustomer(user);
        order.setPlacedTime(LocalDateTime.now());
        order.setProducts(orderProdeuctList);
        order.setTotalAmount(totalAmount);


        order=databaseApi.saveOrder(order);
        bill.setOrderId(order.getId());
        bill.setOrderPlacedTime(LocalDateTime.now());
        bill.setTotalBilledPayed(totalAmount);
        bill.setProduct(billProduct);

        notifyDeliveryPartner(wareHouse.getPinCode(),user,bill);

        return bill;
    }



}

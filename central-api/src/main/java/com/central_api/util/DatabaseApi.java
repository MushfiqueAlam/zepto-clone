package com.central_api.util;

import com.central_api.models.AppUser;
import com.central_api.models.Product;
import com.central_api.models.WareHouse;
import com.central_api.models.WareHouseProducts;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//import java.lang.stereotype.Type;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class DatabaseApi extends ApiUtilImpl{
//    String dbApiUrl="/api/v1/db";

    @Value("${db.api.url}")
    String dbApiUrl;
    @Autowired
    ModelMapper mapper;

    public AppUser callCreateUserEndPoint(AppUser user){
        //create the url
//        String url="http://localhost:8080"+dbApiUrl+"/user/save";
//        URI finalUrl=URI.create(url);
//
//        //create request entity
//        RequestEntity request=RequestEntity.post(finalUrl).body(user);
//
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<AppUser> response=restTemplate.exchange(url, HttpMethod.POST,request,AppUser.class);
//        return response.getBody();

       //  String url = "http://localhost:8081" + dbApiUrl + "/user/save";
        //        URI finalUrl = URI.create(url);
        //        //create request entity
        //        RequestEntity request = RequestEntity.post(finalUrl).body(user);
        //        // Create rest Template
        //        RestTemplate restTemplate = new RestTemplate();
        //        ResponseEntity<AppUser> response =restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        //        return response.getBody();

        Object response=makePostCall(dbApiUrl,"/user/save",new HashMap<>(),user);
        AppUser userResponse=mapper.map(response, AppUser.class);
        return userResponse;
    }

    public Product callCreateProductEndPoint(Product product){
        Object response=makePostCall(dbApiUrl,"/product/save",new HashMap<>(),product);
        return mapper.map(response,Product.class);
    }

    public AppUser getUserByEmail(String email) {
        String endPoint="/user/findByEmail/"+email;
        Object response=makeGetCall(dbApiUrl,endPoint,new HashMap<>());
        if (response == null) {
            // Handle the case where the user is not found
            return null; // or throw a custom exception
        }
        return mapper.map(response,AppUser.class);
    }

    public AppUser getUserByUserId(UUID userId) {
        //need to call th dbApi
        String endPoint="/user/"+userId.toString();
        Object response=makeGetCall(dbApiUrl,endPoint,new HashMap<>());
        return mapper.map(response, AppUser.class);
    }

    public WareHouse createWareHouse(WareHouse wareHouse) {
        String endPoint="/warehouse/save";
        Object response=makePostCall(dbApiUrl,endPoint,new HashMap<>(),wareHouse);
        return mapper.map(response,WareHouse.class);

    }

    public WareHouseProducts createWareHouseProducts(WareHouseProducts products){
        String endPoint="/warehouse/products/save";
        Object response=makePostCall(dbApiUrl,endPoint,new HashMap<>(),products);
        return mapper.map(response, WareHouseProducts.class);
    }

    public WareHouse getWareHouseByPinCode(int pinCode) {
        String endPoint="/warehouse/pinCode/"+pinCode;
        Object response=makeGetCall(dbApiUrl,endPoint,new HashMap<>());
        if(response==null){
            return null;
        }
        return mapper.map(response,WareHouse.class);
    }

    public Product getProductByProductId(UUID pId) {
        String endPoint="/product/"+pId.toString();;
        Object response=makeGetCall(dbApiUrl,endPoint,new HashMap<>());
        return mapper.map(response, Product.class);
    }

    public List<WareHouseProducts> getProductByWareHouseId(UUID wId) {
        String endPoint="/warehouse/product/"+wId.toString();
        Object response=makeGetCall(dbApiUrl,endPoint,new HashMap<>());
        Type listType=new TypeToken<List<WareHouseProducts>>(){}.getType();

        return mapper.map(response,listType);
    }
}

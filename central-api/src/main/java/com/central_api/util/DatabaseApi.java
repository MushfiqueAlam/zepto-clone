package com.central_api.util;

import com.central_api.models.AppUser;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class DatabaseApi {
    String dbApiUrl="/api/v1/db";

    public AppUser callCreateUserEndPoint(AppUser user){
        //create the url
        String url="http://localhost:8080"+dbApiUrl+"/user/save";
        URI finalUrl=URI.create(url);

        //create request entity
        RequestEntity request=RequestEntity.post(finalUrl).body(user);

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<AppUser> response=restTemplate.exchange(url, HttpMethod.POST,request,AppUser.class);
        return response.getBody();
    }
}

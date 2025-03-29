package com.central_api.util;

import com.central_api.requestDto.RequestOrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MailUtil extends ApiUtilImpl{
    @Value("mail.api.url")
    String mailApiUrl;

    public void sendNotification(RequestOrderDto orderDto){

        String endPoint="/delivery-partner/order/notify";
        Object response=makePutCall(mailApiUrl,endPoint,new HashMap<>(),orderDto);

    }

    public void sendAcceptNotification(RequestOrderDto orderDto) {
        String endPoint="/delivery-partner/order/accept/notify";
        Object response=makePutCall(mailApiUrl,endPoint,new HashMap<>(),orderDto);

    }
}

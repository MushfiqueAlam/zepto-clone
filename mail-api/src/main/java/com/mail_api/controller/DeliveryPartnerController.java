package com.mail_api.controller;

import com.mail_api.requestbody.RequestOrderDto;
import com.mail_api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail/delivery-partner")
public class DeliveryPartnerController {
    @Autowired
    MailService mailService;

    @PutMapping("/order/notify")
    public String orderNotification(@RequestBody RequestOrderDto requestOrderDto) throws Exception {
        mailService.sendOrderNotificationToDeliveryPartner(requestOrderDto);
        return "Success";
    }

    @PutMapping("/order/accept/notify")
    public String acceptMail(@RequestBody RequestOrderDto requestOrderDto) throws Exception{
        mailService.notifyCustomerForOrderAssignment(requestOrderDto);
        mailService.notifyDeliveryPartnerForOrderAcceptance(requestOrderDto);
        return "Success";
    }
}

package com.mail_api.service;

import com.mail_api.requestbody.RequestOrderDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

//import javax.naming.Context;

@Service
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendOrderNotificationToDeliveryPartner(RequestOrderDto orderDto) throws Exception {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message);
        mimeMessageHelper.setTo(orderDto.getDeliveryPartner().getEmail());
        mimeMessageHelper.setSubject("A new order has been placed. please review the detail");

        Context context=new Context();

        context.setVariable("deliveryPartnerName",orderDto.getDeliveryPartner().getName());
        context.setVariable("customerName",orderDto.getCustomer().getName());
        context.setVariable("customerPhone",orderDto.getCustomer().getPhoneNumber());
        context.setVariable("customerAddress",orderDto.getCustomer().getAddress());
        context.setVariable("orderId",orderDto.getBill().getOrderId());
        context.setVariable("productList",orderDto.getBill().getProducts());
        context.setVariable("totalBills",orderDto.getBill().getTotalBillPayed());


        String htmlTemplate=templateEngine.process("order-notification",context);
        mimeMessageHelper.setText(htmlTemplate,true);
        mailSender.send(message);

    }
}

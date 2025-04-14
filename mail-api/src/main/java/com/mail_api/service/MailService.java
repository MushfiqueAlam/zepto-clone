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

//        define base url
        String baseUrl="http://localhost:8082";
//      construct full URl
        String acceptUrl=baseUrl+"/api/v1/central/order/accept/"+
                orderDto.getDeliveryPartner().getId().toString()+"/"+orderDto.getBill().getOrderId().toString();

        Context context=new Context();

        context.setVariable("deliveryPartnerName",orderDto.getDeliveryPartner().getName());
        context.setVariable("customerName",orderDto.getCustomer().getName());
        context.setVariable("customerPhone",orderDto.getCustomer().getPhoneNumber());
        context.setVariable("customerAddress",orderDto.getCustomer().getAddress());
        context.setVariable("orderId",orderDto.getBill().getOrderId());
        context.setVariable("productList",orderDto.getBill().getProducts());
        context.setVariable("totalBills",orderDto.getBill().getTotalBillPayed());

        context.setVariable("acceptEndPoint",acceptUrl);

        String htmlTemplate=templateEngine.process("order-notification",context);
        mimeMessageHelper.setText(htmlTemplate,true);
        mailSender.send(message);

    }



    public void notifyDeliveryPartnerForOrderAcceptance(RequestOrderDto requestOrderDto) throws Exception{
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        Context context=new Context();
        context.setVariable("customerName",requestOrderDto.getCustomer().getName());
        context.setVariable("customerPhone",requestOrderDto.getCustomer().getPhoneNumber());
        context.setVariable("customerAddress",requestOrderDto.getCustomer().getAddress());
        context.setVariable("orderId",requestOrderDto.getOrder().getId());
        context.setVariable("pickupLocation",requestOrderDto.getDeliveryPartner().getAddress());


        String htmlTemplate=templateEngine.process("order-accept-notification",context);

        helper.setTo(requestOrderDto.getDeliveryPartner().getEmail());
        helper.setSubject("Order Assigned");
        helper.setText(htmlTemplate,true);
        mailSender.send(message);
    }

    public void notifyCustomerForOrderAssignment(RequestOrderDto requestOrderDto) throws Exception{

        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);

        Context context=new Context();
        context.setVariable("deliveryPartnerName",requestOrderDto.getDeliveryPartner().getName());
        context.setVariable("customerName",requestOrderDto.getCustomer().getName());
        context.setVariable("deliveryPartnerPhone",requestOrderDto.getDeliveryPartner().getPhoneNumber());
        context.setVariable("vehicleNumber",3948);

        String htmlTemplate=templateEngine.process("order-assigned",context);

        helper.setText(htmlTemplate,true);
        helper.setSubject("Order Assigned");
        helper.setText(requestOrderDto.getCustomer().getEmail());

        mailSender.send(message);
    }
}

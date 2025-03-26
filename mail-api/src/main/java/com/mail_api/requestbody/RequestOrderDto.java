package com.mail_api.requestbody;

import lombok.Data;

@Data
public class RequestOrderDto {
    AppUser customer;
    AppUser deliveryPartner;
    ResponseBillDto bill;
    AppOrder order;
}

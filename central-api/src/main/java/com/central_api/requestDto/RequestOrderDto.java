package com.central_api.requestDto;

import com.central_api.models.AppOrder;
import com.central_api.models.AppUser;
import com.central_api.responseDto.ResponseBillDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestOrderDto {
    AppUser customer;
    AppUser deliveryPartner;
    ResponseBillDto bill;
    AppOrder order;
}

package com.rennixin.travelmanagementapp.service;

import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentService {

    public OrderPaymentDemand createOrderPaymentDemand() {
        return OrderPaymentDemand.builder().id(122l).build();
    }
}

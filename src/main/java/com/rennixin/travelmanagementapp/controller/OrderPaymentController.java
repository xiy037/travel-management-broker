package com.rennixin.travelmanagementapp.controller;

import com.rennixin.travelmanagementapp.dtos.OrderPaymentRequest;
import com.rennixin.travelmanagementapp.dtos.OrderPaymentResponse;
import com.rennixin.travelmanagementapp.service.OrderPaymentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrderPaymentController {
    private final OrderPaymentService orderPaymentService;

    public OrderPaymentController(OrderPaymentService orderPaymentService) {
        this.orderPaymentService = orderPaymentService;
    }

    @PostMapping("/orders/{id}/payments")
    public OrderPaymentResponse createPaymentDemand(@PathVariable("id") Long orderId,
                                                    @RequestBody @Valid OrderPaymentRequest orderPaymentRequest) throws Exception {
        var demand = orderPaymentService.createOrderPaymentDemand(orderId, orderPaymentRequest);
        return OrderPaymentResponse.builder()
                .id(demand.getId())
                .createdAt(demand.getCreatedAt().toString())
                .expiredAt(demand.getExpiredAt().toString())
                .build();
    }

}

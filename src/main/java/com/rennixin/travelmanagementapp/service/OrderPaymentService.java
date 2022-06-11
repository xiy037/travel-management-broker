package com.rennixin.travelmanagementapp.service;

import com.rennixin.travelmanagementapp.dtos.OrderPaymentRequest;
import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import com.rennixin.travelmanagementapp.exception.OrderNotFoundException;
import com.rennixin.travelmanagementapp.repository.OrderPaymentDemandRepository;
import com.rennixin.travelmanagementapp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderPaymentService {
    private final OrderPaymentDemandRepository orderPaymentDemandRepository;
    private final OrderRepository orderRepository;

    public OrderPaymentService(OrderPaymentDemandRepository orderPaymentDemandRepository,
                               OrderRepository orderRepository) {
        this.orderPaymentDemandRepository = orderPaymentDemandRepository;
        this.orderRepository = orderRepository;
    }

    public OrderPaymentDemand createOrderPaymentDemand(Long orderId, OrderPaymentRequest request) {
        boolean orderExists = orderRepository.existsById(orderId);
        if (orderExists) {
            //TODO: check record id

            LocalDateTime createdAt = LocalDateTime.now();
            LocalDateTime expiredAt = createdAt.plusDays(1);
            OrderPaymentDemand demand = OrderPaymentDemand.builder()
                    .createdAt(createdAt)
                    .expiredAt(expiredAt)
                    .build();
            return orderPaymentDemandRepository.save(demand);
        } else {
            throw new OrderNotFoundException("order not found");
        }
    }
}

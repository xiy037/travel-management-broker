package com.rennixin.travelmanagementapp.service;

import com.rennixin.travelmanagementapp.data.client.FinanceClient;
import com.rennixin.travelmanagementapp.dtos.OrderPaymentRequest;
import com.rennixin.travelmanagementapp.dtos.RecordDto;
import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import com.rennixin.travelmanagementapp.exception.EntityNotFoundException;
import com.rennixin.travelmanagementapp.data.OrderPaymentDemandRepository;
import com.rennixin.travelmanagementapp.data.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderPaymentService {
    private final OrderPaymentDemandRepository orderPaymentDemandRepository;
    private final OrderRepository orderRepository;
    private final FinanceClient financeClient;

    public OrderPaymentService(OrderPaymentDemandRepository orderPaymentDemandRepository,
                               OrderRepository orderRepository, FinanceClient financeClient) {
        this.orderPaymentDemandRepository = orderPaymentDemandRepository;
        this.orderRepository = orderRepository;
        this.financeClient = financeClient;
    }

    public OrderPaymentDemand createOrderPaymentDemand(Long orderId, OrderPaymentRequest request) {
        boolean orderExists = orderRepository.existsById(orderId);
        if (orderExists) {
            RecordDto record = financeClient.getRecord(request.getRecordId());

            LocalDateTime createdAt = LocalDateTime.now();
            LocalDateTime expiredAt = createdAt.plusDays(1);
            OrderPaymentDemand demand = OrderPaymentDemand.builder()
                    .price(record.getPrice())
                    .unit(record.getUnit())
                    .createdAt(createdAt)
                    .expiredAt(expiredAt)
                    .build();
            return orderPaymentDemandRepository.save(demand);
        } else {
            throw new EntityNotFoundException("order not found");
        }
    }
}

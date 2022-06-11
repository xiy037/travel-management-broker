package com.rennixin.travelmanagementapp.service;

import com.rennixin.travelmanagementapp.dtos.OrderPaymentRequest;
import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import com.rennixin.travelmanagementapp.repository.OrderPaymentDemandRepository;
import com.rennixin.travelmanagementapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class OrderPaymentServiceTest {
    private OrderPaymentService orderPaymentService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderPaymentDemandRepository orderPaymentDemandRepository;

    @BeforeEach
    void init() {
        orderPaymentService = new OrderPaymentService(orderPaymentDemandRepository, orderRepository);
    }

    @Test
    public void should_return_saved_order_payment_demand_object_when_order_and_record_exist() {
        long orderId = 1l;
        OrderPaymentRequest request = OrderPaymentRequest.builder().recordId(100l).build();
        OrderPaymentDemand savedEntity = OrderPaymentDemand.builder()
                .id(111l)
                .createdAt(LocalDateTime.of(2022, 06, 01, 12, 0))
                .expiredAt(LocalDateTime.of(2022, 06, 10, 0, 0))
                .build();

        when(orderRepository.existsById(orderId)).thenReturn(true);
        when(orderPaymentDemandRepository.save(any())).thenReturn(savedEntity);

        OrderPaymentDemand result = orderPaymentService.createOrderPaymentDemand(orderId, request);
        assertThat(result).isEqualTo(savedEntity);
    }


}

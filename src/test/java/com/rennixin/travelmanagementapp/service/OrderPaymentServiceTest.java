package com.rennixin.travelmanagementapp.service;

import com.rennixin.travelmanagementapp.client.FinanceClient;
import com.rennixin.travelmanagementapp.dtos.OrderPaymentRequest;
import com.rennixin.travelmanagementapp.dtos.RecordDto;
import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import com.rennixin.travelmanagementapp.exception.EntityNotFoundException;
import com.rennixin.travelmanagementapp.repository.OrderPaymentDemandRepository;
import com.rennixin.travelmanagementapp.repository.OrderRepository;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Mock
    private FinanceClient financeClient;

    @BeforeEach
    void init() {
        orderPaymentService = new OrderPaymentService(orderPaymentDemandRepository, orderRepository, financeClient);
    }

    @Test
    public void should_return_saved_order_payment_demand_object_when_order_and_record_exist() {
        long orderId = 1l;
        long recordId = 100l;
        OrderPaymentRequest request = OrderPaymentRequest.builder().recordId(recordId).build();
        RecordDto record = RecordDto.builder()
                .id(recordId)
                .price("29000")
                .unit("CNY")
                .build();
        OrderPaymentDemand savedEntity = OrderPaymentDemand.builder()
                .id(111l)
                .price("29000")
                .unit("CNY")
                .createdAt(LocalDateTime.of(2022, 06, 01, 12, 0))
                .expiredAt(LocalDateTime.of(2022, 06, 10, 0, 0))
                .build();

        when(orderRepository.existsById(orderId)).thenReturn(true);
        when(financeClient.getRecord(recordId)).thenReturn(record);
        when(orderPaymentDemandRepository.save(any())).thenReturn(savedEntity);

        OrderPaymentDemand result = orderPaymentService.createOrderPaymentDemand(orderId, request);

        assertThat(result).isEqualTo(savedEntity);
    }

    @Test
    void should_throw_entity_not_found_exception_when_order_not_existed() {
        long orderId = 10009l;
        OrderPaymentRequest request = OrderPaymentRequest.builder().recordId(111l).build();

        when(orderRepository.existsById(orderId)).thenReturn(false);

        EntityNotFoundException thrownException = assertThrows(EntityNotFoundException.class, () -> {
            orderPaymentService.createOrderPaymentDemand(orderId, request);
        });
        assertThat(thrownException.getMessage()).isEqualTo("order not found");
    }

    @Test
    void should_throw_entity_not_found_exception_when_record_not_existed() {
        long orderId = 10009l;
        OrderPaymentRequest request = OrderPaymentRequest.builder().recordId(111l).build();

        when(orderRepository.existsById(orderId)).thenReturn(true);
        when(financeClient.getRecord(111l))
                .thenThrow(new EntityNotFoundException("record not found"));

        EntityNotFoundException thrownException = assertThrows(EntityNotFoundException.class, () -> {
            orderPaymentService.createOrderPaymentDemand(orderId, request);
        });

        assertThat(thrownException.getMessage()).isEqualTo("record not found");
    }


}

package com.rennixin.travelmanagementapp.controller;

import com.rennixin.travelmanagementapp.dtos.OrderPaymentRequest;
import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import com.rennixin.travelmanagementapp.service.OrderPaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(OrderPaymentController.class)
@AutoConfigureJsonTesters
public class OrderPaymentControllerTest {

    @MockBean
    private OrderPaymentService orderPaymentService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JacksonTester<OrderPaymentRequest> orderPaymentJson;

    @AfterEach
    public void reset() {
        Mockito.reset(orderPaymentService);
    }

    @Test
    public void should_return_200_and_order_payment_response_when_apply_for_payment_request_successfully() throws Exception {
        long orderId = 1l;
        OrderPaymentRequest request = OrderPaymentRequest.builder().recordId(100l).build();
        OrderPaymentDemand demand = OrderPaymentDemand.builder()
                .id(111l)
                .createdAt(LocalDateTime.of(2022, 06, 01, 12, 0))
                .expiredAt(LocalDateTime.of(2022, 06, 10, 0, 0))
                .build();
        when(orderPaymentService.createOrderPaymentDemand(orderId, request)).thenReturn(demand);

        MockHttpServletRequestBuilder requestBuilder = post("/orders/{id}/payments", orderId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderPaymentJson.write(request).getJson());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(111)))
                .andExpect(jsonPath("$.createdAt", is("2022-06-01T12:00")))
                .andExpect(jsonPath("$.expiredAt", is("2022-06-10T00:00")));
    }

}

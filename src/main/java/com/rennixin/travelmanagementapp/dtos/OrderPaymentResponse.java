package com.rennixin.travelmanagementapp.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPaymentResponse {
    private Long id;

    private String createdAt;

    private String expiredAt;
}

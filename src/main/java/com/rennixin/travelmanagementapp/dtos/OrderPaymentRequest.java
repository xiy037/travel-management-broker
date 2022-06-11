package com.rennixin.travelmanagementapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderPaymentRequest {
    @NotNull(message = "recordId cannot be null")
    private Long recordId;
}

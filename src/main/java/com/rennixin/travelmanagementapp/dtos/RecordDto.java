package com.rennixin.travelmanagementapp.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordDto {
    private Long id;

    private String price;

    private String unit;
}

package com.rennixin.travelmanagementapp.client;

import com.rennixin.travelmanagementapp.dtos.RecordDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "financeSystem",
        url = "https://mockrecord.mocklab.io/",
        configuration = RecordClientConfiguration.class)
public interface FinanceClient {
    @GetMapping("/monthly-receipts/{recordId}")
    RecordDto getRecord(@PathVariable("recordId") Long id);
}

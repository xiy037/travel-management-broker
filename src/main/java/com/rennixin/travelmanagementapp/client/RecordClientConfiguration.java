package com.rennixin.travelmanagementapp.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecordClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RecordErrorDecoder();
    }
}

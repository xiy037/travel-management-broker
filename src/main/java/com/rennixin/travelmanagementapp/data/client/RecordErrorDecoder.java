package com.rennixin.travelmanagementapp.data.client;

import com.rennixin.travelmanagementapp.exception.EntityNotFoundException;
import com.rennixin.travelmanagementapp.exception.ServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class RecordErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 404:
                return new EntityNotFoundException("record not found");
            default:
                return new ServiceUnavailableException("record service unavailable");
        }
    }
}

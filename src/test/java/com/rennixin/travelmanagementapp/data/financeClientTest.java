package com.rennixin.travelmanagementapp.data;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.rennixin.travelmanagementapp.data.client.FinanceClient;
import com.rennixin.travelmanagementapp.dtos.RecordDto;
import com.rennixin.travelmanagementapp.exception.EntityNotFoundException;
import com.rennixin.travelmanagementapp.exception.ServiceUnavailableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestPropertySource("/test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 9999)
public class financeClientTest {

    @Autowired
    private FinanceClient financeClient;

    @Test
    public void should_return_a_record_when_getting_record_successfully() {
        RecordDto recordDto = financeClient.getRecord(200l);
        assertThat(recordDto.getId()).isEqualTo(20);
        assertThat(recordDto.getPrice()).isEqualTo("12899");
        assertThat(recordDto.getUnit()).isEqualTo("CNY");
    }

    @Test
    public void should_throw_entity_not_found_exception_when_return_404() {
        EntityNotFoundException thrownException = assertThrows(EntityNotFoundException.class, () -> {
            financeClient.getRecord(111l);
        });
        assertThat(thrownException.getMessage()).isEqualTo("record not found");
    }

    @Test
    public void should_throw_service_unavailable_exception_when_return_503() {
        ServiceUnavailableException thrownException = assertThrows(ServiceUnavailableException.class, () -> {
            financeClient.getRecord(100l);
        });
        assertThat(thrownException.getMessage()).isEqualTo("record service unavailable");
    }
}

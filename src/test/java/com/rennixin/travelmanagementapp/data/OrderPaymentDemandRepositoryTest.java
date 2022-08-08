package com.rennixin.travelmanagementapp.data;

import com.rennixin.travelmanagementapp.entity.OrderPaymentDemand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestPropertySource("/test.properties")
public class OrderPaymentDemandRepositoryTest {

    @Autowired
    private OrderPaymentDemandRepository orderPaymentDemandRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_save_a_order_payment_demand() {
        LocalDateTime createdAt = LocalDateTime.of(2022, 6, 1, 1, 1);
        LocalDateTime expiredAt = LocalDateTime.of(2022, 6, 2, 1, 1);
        OrderPaymentDemand entity = OrderPaymentDemand.builder()
                .price("100")
                .unit("CNY")
                .createdAt(createdAt)
                .expiredAt(expiredAt)
                .build();
        OrderPaymentDemand savedDemand = orderPaymentDemandRepository.save(entity);

        assertThat(savedDemand.getId()).isNotNull();
        assertThat(savedDemand.getPrice()).isEqualTo("100");
        assertThat(savedDemand.getUnit()).isEqualTo("CNY");
        assertThat(savedDemand.getCreatedAt()).isEqualTo(createdAt);
        assertThat(savedDemand.getExpiredAt()).isEqualTo(expiredAt);
    }

}

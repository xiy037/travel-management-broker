package com.rennixin.travelmanagementapp.Repository;

import com.rennixin.travelmanagementapp.entity.Order;
import com.rennixin.travelmanagementapp.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestPropertySource("/test.properties")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_true_when_order_with_the_given_id_exists() {
        var id =(Long) entityManager.persistAndGetId(Order.builder()
                .clientName("TW")
                .clientId("999")
                .build());
;
        boolean found = orderRepository.existsById(id);

        assertThat(found).isTrue();
    }

}

package com.rennixin.travelmanagementapp.data;

import com.rennixin.travelmanagementapp.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Override
    boolean existsById(Long id);
}

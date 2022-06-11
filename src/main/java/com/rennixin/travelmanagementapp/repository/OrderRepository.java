package com.rennixin.travelmanagementapp.repository;

import com.rennixin.travelmanagementapp.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

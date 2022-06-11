package com.rennixin.travelmanagementapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentDemand {

    @Id
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;
}

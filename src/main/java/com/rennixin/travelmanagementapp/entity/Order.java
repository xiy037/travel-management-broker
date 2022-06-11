package com.rennixin.travelmanagementapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private Long id;

    @NotNull
    private String clientName;

    @NotNull
    private String clientId;

    @CreatedDate
    private LocalDateTime createdAt;



}

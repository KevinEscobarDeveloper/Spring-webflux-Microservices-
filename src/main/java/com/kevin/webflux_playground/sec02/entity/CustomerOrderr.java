package com.kevin.webflux_playground.sec02.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class CustomerOrderr {

    @Id
    private UUID orderId;
    private Integer customerId;
    private Integer productId;
    private Integer amount;
    private Instant orderDate;
}

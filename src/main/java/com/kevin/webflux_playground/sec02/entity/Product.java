package com.kevin.webflux_playground.sec02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("product")
public class Product {
    @Id
    private Integer id;
    private String description;
    Integer price;
}


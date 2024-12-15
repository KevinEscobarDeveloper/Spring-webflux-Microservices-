package com.kevin.webflux_playground.sec03.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("customer")
public class Customer {

    @Id
    private Integer id;
    @Column("name")
    private String name;
    private String email;
}

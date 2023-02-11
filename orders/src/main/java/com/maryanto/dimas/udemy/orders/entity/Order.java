package com.maryanto.dimas.udemy.orders.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(generator = "uuid_gen")
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @Column(name = "id", length = 64, nullable = false)
    private String id;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "item")
    private String item;
    @Column(name = "qty")
    private Integer qty;
}

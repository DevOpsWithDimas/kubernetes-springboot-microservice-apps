package com.maryanto.dimas.udemy.orders.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {

    private String id;
    private LocalDateTime createdDate;
    private CustomerDTO customer;
    private String item;
    private Integer qty;
}

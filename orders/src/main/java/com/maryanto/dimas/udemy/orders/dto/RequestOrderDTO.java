package com.maryanto.dimas.udemy.orders.dto;

import lombok.Data;

@Data
public class RequestOrderDTO {

    private String userId;
    private String item;
    private Integer qty;
}

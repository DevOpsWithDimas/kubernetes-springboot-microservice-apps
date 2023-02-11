package com.maryanto.dimas.udemy.orders.repository;

import com.maryanto.dimas.udemy.orders.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends
        PagingAndSortingRepository<Order, String>,
        CrudRepository<Order, String> {
}

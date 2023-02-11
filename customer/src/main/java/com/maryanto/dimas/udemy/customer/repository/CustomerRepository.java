package com.maryanto.dimas.udemy.customer.repository;

import com.maryanto.dimas.udemy.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends
        CrudRepository<Customer, String>,
        PagingAndSortingRepository<Customer, String> {

    Optional<Customer> findFirstByUserId(String userId);
}

package com.maryanto.dimas.udemy.customer.controller;

import com.maryanto.dimas.udemy.customer.entity.Customer;
import com.maryanto.dimas.udemy.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer/v1")
public class CustomerController {

    private CustomerRepository repo;

    @Autowired
    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") String id) {
        Optional<Customer> optional = this.repo.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.noContent().build());
    }

    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<Customer> findByUserId(@PathVariable("id") String id) {
        Optional<Customer> optional = this.repo.findFirstByUserId(id);
        return optional.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.noContent().build());
    }
}

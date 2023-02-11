package com.maryanto.dimas.udemy.orders.controller;

import com.maryanto.dimas.udemy.orders.dto.CustomerDTO;
import com.maryanto.dimas.udemy.orders.dto.OrderDTO;
import com.maryanto.dimas.udemy.orders.dto.RequestOrderDTO;
import com.maryanto.dimas.udemy.orders.entity.Order;
import com.maryanto.dimas.udemy.orders.repository.OrderRepository;
import com.maryanto.dimas.udemy.orders.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/order/v1")
public class OrderController {

    private final OrderRepository repo;
    private final CustomerService serviceCustomer;

    @Autowired
    public OrderController(OrderRepository repo, CustomerService serviceCustomer) {
        this.repo = repo;
        this.serviceCustomer = serviceCustomer;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> placeOrder(@RequestBody RequestOrderDTO order) {
        ResponseEntity<CustomerDTO> responseCustomer = this.serviceCustomer.findById(order.getUserId());
        if (responseCustomer.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.badRequest().body("Customer not found!");
        }

        CustomerDTO customer = responseCustomer.getBody();
        Order purchaseOrder = new Order();
        purchaseOrder.setId(UUID.randomUUID().toString());
        purchaseOrder.setCreatedDate(LocalDateTime.now());
        purchaseOrder.setCustomerId(customer.getId());
        purchaseOrder.setItem(order.getItem());
        purchaseOrder.setQty(order.getQty());

        try {
            purchaseOrder = this.repo.save(purchaseOrder);

            OrderDTO output = new OrderDTO();
            output.setId(purchaseOrder.getId());
            output.setCreatedDate(purchaseOrder.getCreatedDate());
            output.setItem(purchaseOrder.getItem());
            output.setCustomer(customer);
            output.setQty(purchaseOrder.getQty());
            return ResponseEntity.ok(output);
        } catch (Exception ex) {
            log.error("Can't proses checkout", ex);
            return ResponseEntity.internalServerError().body("Transaction can't be processed!!! \nPlease report to adminstrator");
        }

    }
}

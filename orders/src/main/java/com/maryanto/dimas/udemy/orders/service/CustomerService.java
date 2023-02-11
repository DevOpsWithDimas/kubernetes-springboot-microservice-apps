package com.maryanto.dimas.udemy.orders.service;

import com.maryanto.dimas.udemy.orders.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    private final String customerHost;
    private final String customerPort;
    private final String customerContextPath;
    private final RestTemplate rest;
    private final String customerProto;

    @Autowired
    public CustomerService(
            RestTemplate rest,
            @Value("${services.customer.host}") String host,
            @Value("${services.customer.port}") String port,
            @Value("${services.customer.proto}") String proto,
            @Value("${services.customer.context-path}") String contextPath) {
        this.customerHost = host;
        this.customerPort = port;
        this.customerProto = proto;
        this.customerContextPath = contextPath;
        this.rest = rest;
    }

    public ResponseEntity<CustomerDTO> findById(String id) {
        String baseUrl = String.format(
                "%s://%s:%s%s/api/customer/v1/findById/",
                this.customerProto, this.customerHost, this.customerPort, this.customerContextPath);
        return this.rest.getForEntity(baseUrl + "{userId}", CustomerDTO.class, id);
    }
}

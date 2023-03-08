package com.maryanto.dimas.udemy.orders.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CustomerAPIHealthIndicator implements HealthIndicator {

    private final String customerHost;
    private final String customerPort;
    private final String customerContextPath;
    private final RestTemplate rest;
    private final String customerProto;

    @Autowired
    public CustomerAPIHealthIndicator(
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

    @Override
    public Health health() {
        String baseUrl = String.format(
                "%s://%s:%s%s/actuator/health/readiness",
                this.customerProto, this.customerHost, this.customerPort, this.customerContextPath);
        try {
            ResponseEntity<String> responseEntity = this.rest.getForEntity(baseUrl, String.class);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                Map<String, String> objects = new HashMap<>();
                objects.put("error", responseEntity.getStatusCode().toString());
                objects.put("url", baseUrl);
                return Health.down()
                        .withDetails(objects)
                        .build();
            } else {
                return Health.up().build();
            }
        } catch (RestClientException rce) {
            log.error("can't connect to service ", rce);
            return Health.down()
                    .withException(rce)
                    .build();
        }
    }
}

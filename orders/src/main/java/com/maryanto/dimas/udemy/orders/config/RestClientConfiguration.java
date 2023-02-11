package com.maryanto.dimas.udemy.orders.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(3))
                .setBufferRequestBody(true)
                .setReadTimeout(Duration.ofSeconds(3))
                .build();
    }
}

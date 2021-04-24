package com.microserviceproject.client.clientapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Application file for the client application.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ClientapplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientapplicationApplication.class, args);
    }

}

package com.microserviceproject.discovery.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Application to start service discovery.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServicediscoveryApplication {

    public static void main(String[] args) { SpringApplication.run(ServicediscoveryApplication.class, args); }

}

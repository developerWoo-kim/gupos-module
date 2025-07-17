package com.gw.guposapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.gw.guposcore.domain")
public class GuposApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuposApiApplication.class, args);
    }
}
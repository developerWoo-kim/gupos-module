package com.gw.guposapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.gw.guposcore.domain")
@EnableJpaRepositories({"com.gw.guposapi", "com.gw.guposcore"})
public class GuposApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuposApiApplication.class, args);
    }
}
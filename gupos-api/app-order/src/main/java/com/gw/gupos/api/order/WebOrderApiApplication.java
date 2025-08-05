package com.gw.gupos.api.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.gw.guposcore.domain")
@EnableJpaRepositories({"com.gw.gupos.api.order", "com.gw.guposcore"})
public class WebOrderApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebOrderApiApplication.class, args);
    }
}
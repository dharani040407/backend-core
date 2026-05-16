package com.codewith.secondapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.codewith.Entities")
@EnableJpaRepositories(basePackages = "com.codewith.secondapi.repository")
public class SecondapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondapiApplication.class, args);
    }
}
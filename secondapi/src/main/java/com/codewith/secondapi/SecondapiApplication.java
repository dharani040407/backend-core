package com.codewith.secondapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.codewith.secondapi.Entities")
public class SecondapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondapiApplication.class, args);
    }
}

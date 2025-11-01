package com.example.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BigDataProject2Application {
    public static void main(String[] args) {
        SpringApplication.run(BigDataProject2Application.class, args);
    }
}

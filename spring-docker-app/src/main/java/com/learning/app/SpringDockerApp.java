package com.learning.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication → enables auto-config, component scan, and bean setup
// basically tells Spring Boot "start everything from here"
@SpringBootApplication
public class SpringDockerApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringDockerApp.class, args);
        System.out.println("App running → http://localhost:8080");
    }
}

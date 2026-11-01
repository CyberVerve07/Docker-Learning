package com.learning.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// This controller handles the root URL: http://localhost:8080/
// Without this, visiting the root gives a 404 error
@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "App is running! Try: /api/hello | /api/greet/YourName | /api/info | /actuator/health";
    }
}


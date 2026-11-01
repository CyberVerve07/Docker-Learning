package com.learning.app.controller;

import com.learning.app.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController → this class handles HTTP requests and returns JSON
// @RequestMapping("/api") → all URLs in this class will start with /api
@RestController
@RequestMapping("/api")
public class HelloController {

    // reads "app.name" and "app.version" values from application.yml
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    // GET /api/hello
    @GetMapping("/hello")
    public Message hello() {
        return new Message("Hello from Spring Boot! App: " + appName + " v" + appVersion, 200, appName);
    }

    // GET /api/greet/John → "Hello, John!"
    // {name} is a dynamic part of the URL
    @GetMapping("/greet/{name}")
    public Message greet(@PathVariable String name) {
        return new Message("Hello, " + name + "! Welcome to Docker Learning", 200, appName);
    }

    // GET /api/info → shows app info + whether we are inside Docker
    @GetMapping("/info")
    public Message info() {
        // DOCKER_ENV is set inside the Dockerfile using ENV keyword
        String env = System.getenv("DOCKER_ENV") != null
                ? System.getenv("DOCKER_ENV")
                : "Running locally";

        return new Message("App: " + appName + " | Version: " + appVersion + " | " + env, 200, appName);
    }
}

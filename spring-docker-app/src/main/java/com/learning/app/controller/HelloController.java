package com.learning.app.controller;

import com.learning.app.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ============================================================
 *  CONTROLLER CLASS — Handles incoming HTTP Requests
 * ============================================================
 *
 *  Think of a Controller like a "receptionist":
 *   - Someone (browser/Postman/curl) sends an HTTP request to a URL
 *   - The controller receives it, does some work, and sends back a response
 *
 *  In plain Java, you would have to manually:
 *    - Open a ServerSocket
 *    - Parse the HTTP request
 *    - Write the HTTP response
 *
 *  Spring Boot does ALL of that for you. You just define methods! ✅
 */

// @RestController = @Controller + @ResponseBody
//   @Controller    → Marks this class as a web controller
//   @ResponseBody  → Whatever the method returns, send it directly as HTTP response body (as JSON)
@RestController

// @RequestMapping: All endpoints in this class will start with "/api"
// Example: "/api/hello", "/api/greet/John"
@RequestMapping("/api")
public class HelloController {

    // ---- Reading values from application.yml ----
    // @Value injects a value from the YAML config file into this variable.
    // ${app.name} means: look for "app.name" in application.yml
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    // ================================================================
    //  ENDPOINT 1: GET /api/hello
    //  Returns a simple welcome message
    // ================================================================
    // @GetMapping → This method handles HTTP GET requests at "/api/hello"
    // It's like saying: "When someone visits this URL, run this method"
    @GetMapping("/hello")
    public Message hello() {
        // Creating a Message object → Spring converts it to JSON automatically
        return new Message(
            "👋 Hello from Spring Boot inside Docker! App: " + appName + " v" + appVersion,
            200,
            appName
        );
    }

    // ================================================================
    //  ENDPOINT 2: GET /api/greet/{name}
    //  Returns a personalized greeting
    //  Example URL: /api/greet/John → "Hello, John!"
    // ================================================================
    // {name} is a path variable — a dynamic part of the URL
    @GetMapping("/greet/{name}")
    public Message greet(
        // @PathVariable extracts the {name} value from the URL
        @PathVariable String name
    ) {
        return new Message(
            "Hello, " + name + "! Welcome to Docker Learning 🐳",
            200,
            appName
        );
    }

    // ================================================================
    //  ENDPOINT 3: GET /api/info
    //  Returns app information (useful to verify inside Docker container)
    // ================================================================
    @GetMapping("/info")
    public Message info() {
        // System.getenv() reads environment variables from the OS/Container
        // Inside Docker, you can pass env variables — this shows them!
        String dockerEnv = System.getenv("DOCKER_ENV") != null
                           ? System.getenv("DOCKER_ENV")
                           : "Running locally (not in Docker)";

        return new Message(
            "App: " + appName + " | Version: " + appVersion + " | Env: " + dockerEnv,
            200,
            appName
        );
    }
}


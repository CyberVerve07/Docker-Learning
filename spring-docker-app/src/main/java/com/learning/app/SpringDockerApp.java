package com.learning.app;

// These are Spring Boot imports — think of them like Java's standard library imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================
 *  MAIN CLASS — Entry point of the Spring Boot application
 * ============================================================
 *
 *  In plain Java, you have:
 *      public static void main(String[] args) { ... }
 *
 *  Spring Boot is the same — it starts here.
 *  The magic is the @SpringBootApplication annotation.
 */

// @SpringBootApplication is a shortcut for 3 annotations combined:
//   1. @Configuration       → This class can define Spring "beans" (objects Spring manages)
//   2. @EnableAutoConfiguration → Spring Boot auto-configures everything it detects
//                                  (e.g., it sees spring-web in pom.xml → starts Tomcat automatically)
//   3. @ComponentScan       → Scans this package and sub-packages for your classes
@SpringBootApplication
public class SpringDockerApp {

    public static void main(String[] args) {
        // SpringApplication.run() bootstraps the entire application:
        //   - Starts the embedded Tomcat web server (port 8080 by default)
        //   - Loads all your controllers, services, etc.
        //   - Reads application.yml for configuration
        SpringApplication.run(SpringDockerApp.class, args);

        System.out.println("===========================================");
        System.out.println("  🚀 Spring Docker App is Running!");
        System.out.println("  📍 URL: http://localhost:8080");
        System.out.println("  📦 Ready to be containerized with Docker!");
        System.out.println("===========================================");
    }
}


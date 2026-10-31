package com.learning.app.model;

/**
 * ============================================================
 *  MODEL CLASS — Represents the data we send back as JSON
 * ============================================================
 *
 *  In Spring Boot, when you return a Java object from a controller,
 *  Spring automatically converts it to JSON using Jackson library.
 *
 *  Example:
 *    Java Object:  new Message("Hello", 200)
 *    JSON output:  { "text": "Hello", "statusCode": 200 }
 *
 *  This is called SERIALIZATION (Java Object → JSON)
 */
public class Message {

    // These are the fields that will appear in the JSON response
    private String text;       // e.g., "Hello from Docker!"
    private int statusCode;    // e.g., 200
    private String appName;    // e.g., "spring-docker-app"

    // ---- Constructor ----
    // Used to create a Message object with all fields at once
    public Message(String text, int statusCode, String appName) {
        this.text = text;
        this.statusCode = statusCode;
        this.appName = appName;
    }

    // ---- Getters ----
    // Jackson (JSON library) uses getters to read field values
    // when converting to JSON. Without getters, JSON would be empty!

    public String getText() {
        return text;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getAppName() {
        return appName;
    }
}


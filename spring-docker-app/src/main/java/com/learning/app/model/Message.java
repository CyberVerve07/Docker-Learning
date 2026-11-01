package com.learning.app.model;

// This class represents the JSON response we send back from the API
// Spring Boot automatically converts this Java object → JSON
// Example output: { "text": "Hello!", "statusCode": 200, "appName": "spring-docker-app" }
public class Message {

    private String text;
    private int statusCode;
    private String appName;

    public Message(String text, int statusCode, String appName) {
        this.text = text;
        this.statusCode = statusCode;
        this.appName = appName;
    }

    // Getters are required — Jackson (JSON library) uses them to read values
    public String getText() { return text; }
    public int getStatusCode() { return statusCode; }
    public String getAppName() { return appName; }
}

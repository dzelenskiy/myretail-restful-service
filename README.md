# myretail-restful-service

Retail REST API exercise using Java 8, Spring Boot and Embedded MongoDB

This program currently uses an Embedded MongoDB instance and will only persist data while it's running.
This data will be cleared on restart of the application.

# Test Report

Unit and Integration test reports are included in the META-INF/surefire-report.html in the artifact (jar file)
Please note currently Maven is configured to skip Integration Test "ProductDetailsServiceTest.java"
The API that "ProductDetailsServiceTest.java" calls reached a rate limit maximum at the time of generating the artifact

# How to run the application

If downloading source code from GitHub:

    Place provided target-redsky-api.properties file in myretail-restful-service/src/main/resources directory
    Navigate to directory in cli / terminal of choice and perform maven command "mvn clean install"
    This should generate myretail-restful-service-0.0.1-SNAPSHOT.jar artifact in the projects target directory
    Navigate to directory containing myretail-restful-service-0.0.1-SNAPSHOT.jar in terminal
    Run java command "java -jar myretail-restful-service-0.0.1-SNAPSHOT.jar"
    Hit ctrl + c in terminal when finished using application

If myretail-restful-service-0.0.1-SNAPSHOT.jar artifact was provided:

    Download myretail-restful-service-0.0.1-SNAPSHOT.jar artifact to directory of choice
    Navigate to directory containing myretail-restful-service-0.0.1-SNAPSHOT.jar in terminal
    Run java command "java -jar myretail-restful-service-0.0.1-SNAPSHOT.jar"
    Hit ctrl + c in terminal when finished using application

# Using the Application

Application runs on localhost:8080

There is only one endpoint: /v1/products/{id}

    Only GET and PUT HttpMethods are allowed for this endpoint

No custom, auth or token headers are required at this time

GET Request:

    Retrieves product matching provided id with associated current price information
    Only need to provide the product id in the path
    For example: localhost:8080/v1/products/13860428
    If product is not found, a 404 response with json body containing errors will be returned
    If product is found, a 200 response with below json body format will be returned:

    {
        "id": 13860428,
        "name": "The Big Lebowski (Blu-ray)",
        "current_price": {
        "value": 7.99,
        "currency_code": "USD"
    }

PUT Request:

    Changes current price information for specified product
    Need to provide the product in the path
    For example: localhost:8080/v1/products/13860428
    Need to provide a json request body in the format below:

    {
        "id": 13860428,
        "name": "The Big Lebowski (Blu-ray)",
        "current_price": {
        "value": 7.99,
        "currency_code": "USD"
    }

    id has to match the id in the path
    id in the path has to correlate to an existing product
    id in the body cannot be null or empty
    body must have a current_price field which is an entity
    value fractional digits cannot exceed 2 digits
    value integral digitas cannot exceed 5 digits
    value may not be less than 0.01
    value may not be more than 99999.99
    currency_code must not be empty and be one of the following valid ISO Currency Codes:

    USD,  // U.S. dollar
    EUR,  // euro
    GBP,  // Great Britain pound (sterling)
    JPY,  // Japanese yen
    CHF,  // Swiss Franc
    AUD,  // Australian dollar
    CAD,  // Canadian dollar
    CNY,  // China yuan renminbi
    NZD,  // New Zealand dollar
    INR,  // Indian rupee
    BZR,  // Brazillian real
    SEK,  // Swedish krona
    ZAR,  // South African rand
    HKD   // Hong Kong dollar 

    If field validation errors occur a 400 response will be returned containing JSON body with errors field:

    {
    "errors": [
        "value must have no more than 5 integral digits and no more than 2 fractional digits"
        ]
    }

# Spring Boot Actuator

This application comes loaded with Spring Boot Actuator and provide endpoints for metrics and diagnostics

The root url for Spring Boot Actuator in the application is localhost:8080/actuator

For more Spring Boot Actuator endpoints and info please see:

https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html

}




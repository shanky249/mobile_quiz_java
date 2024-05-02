package com.logitech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting the application...");
        SpringApplication.run(MainApplication.class, args);
        LOGGER.info("Application started successfully.");
    }
}

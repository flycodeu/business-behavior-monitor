package com.flycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;
@SpringBootApplication
public class Application {

    public static final Logger log = Logger.getLogger(Application.class.getName());
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("xxx");
        log.info("xxsd");
    }
}
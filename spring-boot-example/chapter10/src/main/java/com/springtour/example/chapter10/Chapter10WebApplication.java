package com.springtour.example.chapter10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class Chapter10WebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(Chapter10WebApplication.class, args);
    }
}

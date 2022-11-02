package com.springtour.example.chapter11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Chapter11WebApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(Chapter11WebApplication.class, args);
    }
}

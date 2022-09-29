package com.springtour.example.chapter12;

import com.springtour.example.chapter12.event.server.ApplicationEventListener;
import com.springtour.example.chapter12.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Chapter12SyncApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(Chapter12SyncApplication.class);
        SpringApplication application = appBuilder.build();
        application.addListeners(new ApplicationEventListener());

        ConfigurableApplicationContext ctxt = application.run(args);

        UserService userService = ctxt.getBean(UserService.class);
        userService.createUser("Byungboo Kim", "test.com");
    }
}


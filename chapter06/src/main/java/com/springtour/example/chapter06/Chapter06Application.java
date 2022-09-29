package com.springtour.example.chapter06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.springtour.example.chapter06")
public class Chapter06Application {

    private static final String PROFILE_DELIMITER = ",";

    public static void main(String[] args) {

        String activeProfileLine = System.getProperty("spring.profiles.active");
        String[] activeProfiles = activeProfileLine.split(PROFILE_DELIMITER);
        if (activeProfiles == null || activeProfiles.length != 2)
            throw new RuntimeException("Active Profiles Error. " + activeProfileLine);

        SpringApplication.run(Chapter06Application.class, args);
    }
}

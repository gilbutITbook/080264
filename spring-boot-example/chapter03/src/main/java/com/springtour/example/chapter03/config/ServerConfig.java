package com.springtour.example.chapter03.config;

import com.springtour.example.chapter03.domain.format.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean
    public String datePattern() {
        return "yyyy-MM-dd'T'HH:mm:ss.XXX";
    }

    @Bean
    public DateFormatter defaultDateFormatter() {
        return new DateFormatter(datePattern());
    }
}

package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.format.Formatter;
import com.springtour.example.chapter03.domain.format.LocalDateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class SpringBean02Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(SpringBean02Application.class);

        Formatter<LocalDateTime> formatter = ctxt.getBean("localDateTimeFormatter", Formatter.class);
        String date = formatter.of(LocalDateTime.of(2020, 12, 24, 23, 59, 59));

        log.info("Date : " + date);

        ctxt.close();
    }
}

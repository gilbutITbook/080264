package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.format.Formatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class SpringBean04Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(SpringBean04Application.class);

        Formatter formatter = applicationContext.getBean("localDateFormatter", Formatter.class);
        String dateString = formatter.of(new Date());
        log.info("Date : {}", dateString);

        applicationContext.close();
    }
}

package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Locale;

@Slf4j
@SpringBootApplication
public class SpringBean09Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean09Application.class);
        log.info("------- Done to initialize spring beans");
        PriceUnit priceUnit = ctxt.getBean("lazyPriceUnit",PriceUnit.class);
        log.info("Locale in PriceUnit : {}", priceUnit.getLocale().toString());
        ctxt.close();
    }

    @Bean
    @Lazy
    public PriceUnit lazyPriceUnit() {
        log.info("initialize lazyPriceUnit");
        return new PriceUnit(Locale.US);
    }
}

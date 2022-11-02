package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.ProductOrder;
import com.springtour.example.chapter03.service.Printer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class SpringBean03Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(SpringBean03Application.class);

        Printer printer = applicationContext.getBean(Printer.class);
        ProductOrder order = new ProductOrder(BigDecimal.valueOf(1000), LocalDateTime.now(), "byungboo.kim");

        try (OutputStream os = System.out) {
            printer.print(os, order);
        } catch (IOException ioe) {
            log.error("Error to print", ioe);
        }

        applicationContext.close();
    }
}

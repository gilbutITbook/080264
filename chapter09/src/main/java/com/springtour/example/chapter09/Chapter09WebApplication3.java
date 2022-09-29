package com.springtour.example.chapter09;

import com.springtour.example.chapter09.adapter.PoolingBillingAdapter;
import com.springtour.example.chapter09.adapter.WebClientBillingAdapter;
import com.springtour.example.chapter09.controller.CreateCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Chapter09WebApplication3 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(Chapter09WebApplication3.class, args);

        WebClientBillingAdapter billingAdapter = ctxt.getBean(WebClientBillingAdapter.class);

        CreateCodeResponse codeResponse =
                billingAdapter.createBillingCode(List.of(19000L, 18000L, 17000L));
        log.info("Result : {}", codeResponse);
    }
}

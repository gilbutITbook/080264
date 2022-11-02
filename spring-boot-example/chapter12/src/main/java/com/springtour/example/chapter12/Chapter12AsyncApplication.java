package com.springtour.example.chapter12;

import com.springtour.example.chapter12.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@AllArgsConstructor
@SpringBootApplication
public class Chapter12AsyncApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(Chapter12SyncApplication.class, args);

        HotelService hotelService = ctxt.getBean(HotelService.class);
        hotelService.createHotel("The Ritz-Carlton, Marina del Rey", "4375 Admiralty Way, Marina Del Rey, CA 90292");
    }
}

package com.springtour.example.chapter12.event.hotel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HotelEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public HotelEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishHotelCreated(Long hotelId, String address) {
        HotelCreateEvent event = HotelCreateEvent.of(hotelId, address);
        log.info("Publish hotel created event.");
        applicationEventPublisher.publishEvent(event);
    }

}

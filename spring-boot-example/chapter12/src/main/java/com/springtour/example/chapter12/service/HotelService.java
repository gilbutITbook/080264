package com.springtour.example.chapter12.service;

import com.springtour.example.chapter12.event.hotel.HotelEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelService {

    private final HotelEventPublisher hotelEventPublisher;

    public HotelService(HotelEventPublisher hotelEventPublisher) {
        this.hotelEventPublisher = hotelEventPublisher;
    }

    public Boolean createHotel(String hotelName, String hotelAddress) {
        // 호텔 생성 로직 생략
        log.info("created hotel. {}, {}", hotelName, hotelAddress);
        hotelEventPublisher.publishHotelCreated(999111222L, hotelAddress);
        log.info("done create hotel");
        return Boolean.TRUE;
    }
}

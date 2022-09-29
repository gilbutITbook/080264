package com.springtour.example.chapter10.service;

import lombok.Getter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

@Getter
@ToString
@Cacheable
public class HotelRequest {
    private Long hotelId;

    public HotelRequest(Long hotelId) {
        this.hotelId = hotelId;
    }
}

package com.springtour.example.chapter10.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Cacheable(value = "hotelCache")
    public HotelResponse getHotelById(Long hotelId) {
        return new HotelResponse(hotelId, "Line Hotel", "3515 Wilshire Blvd, Los Angeles, CA 90010");
    }

    @Cacheable(
            value = "hotelCache",
            key = "#hotelName",
            condition = "#hotelName > '' && #hotelAddress.length() > 10"
    )
    public HotelResponse getHotelNameAndAddress(String hotelName, String hotelAddress) {
        return new HotelResponse(234234L, hotelName, hotelAddress);
    }

    @Cacheable(
            value = "hotelCache",
            keyGenerator = "hotelKeyGenerator"
    )
    public HotelResponse getHotel(HotelRequest hotelRequest) {
        return new HotelResponse(hotelRequest.getHotelId(), "The shilla", "249, Dongho-ro, Jung-gu, Seoul");
    }
}

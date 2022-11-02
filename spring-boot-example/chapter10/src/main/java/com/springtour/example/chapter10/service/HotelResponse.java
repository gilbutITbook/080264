package com.springtour.example.chapter10.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HotelResponse {

    private Long hotelId;
    private String hotelName;
    private String hotelAddress;

    public HotelResponse() {
    }

    public HotelResponse(Long hotelId, String hotelName, String hotelAddress) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
    }
}

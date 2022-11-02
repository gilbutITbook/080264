package com.springtour.example.chapter07.controller;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class HotelResponse {

    private Long hotelId;
    private String hotelName;
    private String address;
    private String phoneNumber;

    public HotelResponse(Long hotelId, String hotelName, String address, String phoneNumber) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

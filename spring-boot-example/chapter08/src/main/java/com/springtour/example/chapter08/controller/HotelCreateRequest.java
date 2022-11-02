package com.springtour.example.chapter08.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelCreateRequest {

    private String name;
    private String address;
    private String phoneNumber;
    private Integer roomCount;

}

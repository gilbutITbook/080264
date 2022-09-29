package com.springtour.example.chapter07.service;

import com.springtour.example.chapter07.controller.HotelRequest;
import com.springtour.example.chapter07.controller.HotelResponse;

import java.util.List;

public interface DisplayService {

    List<HotelResponse> getHotelsByName(HotelRequest hotelRequest);
}

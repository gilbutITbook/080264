package com.springtour.example.chapter08.controller;

import com.springtour.example.chapter08.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(path = "/hotels")
    public ResponseEntity<HotelCreateResponse> createHotel(@RequestBody HotelCreateRequest request) {
        HotelCreateResponse response = hotelService.createHotel(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/hotels/{hotelId}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable("hotelId") Long hotelId) {
        HotelResponse hotelResponse = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelResponse);
    }
}

package com.springtour.example.chapter05.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.List;

public class RoomController {

    @PostMapping(path = "/hotels/{hotelId}/rooms")
    public ResponseEntity createHotelRoom(
            @PathVariable Long hotelId,
            @Valid  @RequestBody List<HotelRoomRequest> hotelRoomRequests,
            BindingResult bindingResult
    ) {
        // create hotel rooms
        return ResponseEntity.ok(null);
    }
}

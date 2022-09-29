package com.springtour.example.chapter07.controller;

import com.springtour.example.chapter07.service.HotelDisplayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class HotelController {

    private HotelDisplayService hotelDisplayService;

    public HotelController(HotelDisplayService hotelDisplayService) {
        this.hotelDisplayService = hotelDisplayService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/hotels/fetch-by-name")
    public ResponseEntity<List<HotelResponse>> getHotelByName(@RequestBody HotelRequest hotelRequest) {
        List<HotelResponse> hotelResponses = hotelDisplayService.getHotelsByName(hotelRequest);
        return ResponseEntity.ok(hotelResponses);
    }
}

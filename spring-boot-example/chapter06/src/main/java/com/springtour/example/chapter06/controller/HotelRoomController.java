package com.springtour.example.chapter06.controller;


import com.springtour.example.chapter06.domain.HotelRoomNumber;
import com.springtour.example.chapter06.utils.IdGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@RestController
public class HotelRoomController {

    @GetMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
    public HotelRoomResponse getHotelRoomByPeriod(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate toDate) {

        Long hotelRoomId = IdGenerator.create();
        BigDecimal originalPrice = new BigDecimal("130.00");

        HotelRoomResponse response = HotelRoomResponse.of(hotelRoomId, roomNumber, HotelRoomType.DOUBLE, originalPrice);
        if (Objects.nonNull(fromDate) && Objects.nonNull(toDate))
            fromDate.datesUntil(toDate.plusDays(1)).forEach(date -> response.reservedAt(date));

        return response;
    }

//    HotelRoomNumber 모델에 대한 데이터 바인딩 예제
//    @GetMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
//    public HotelRoomResponse getHotelRoomByPeriod(
//            @PathVariable Long hotelId,
//            @PathVariable HotelRoomNumber roomNumber,
//            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate fromDate,
//            @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate toDate) {
//
//        Long hotelRoomId = IdGenerator.create();
//        BigDecimal originalPrice = new BigDecimal("130.00");
//
//        HotelRoomResponse response = HotelRoomResponse.of(hotelRoomId, roomNumber.toString(), HotelRoomType.DOUBLE, originalPrice);
//        if (Objects.nonNull(fromDate) && Objects.nonNull(toDate))
//            fromDate.datesUntil(toDate.plusDays(1)).forEach(date -> response.reservedAt(date));
//
//        return response;
//    }
}

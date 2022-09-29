package com.springtour.example.chapter05.controller;

import com.springtour.example.chapter05.controller.validator.HotelRoomReserveValidator;
import com.springtour.example.chapter05.domain.reservation.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HotelRoomReserveController {

    private final ReserveService reserveService;

    public HotelRoomReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(new HotelRoomReserveValidator());
    }

    @PostMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}/reserve")
    public ResponseEntity<HotelRoomIdResponse> reserveHotelRoomByRoomNumber(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            @Valid @RequestBody HotelRoomReserveRequest reserveRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = new StringBuilder(bindingResult.getFieldError().getCode())
                    .append(" [").append(fieldError.getField()).append("] ")
                    .append(fieldError.getDefaultMessage())
                    .toString();

            System.out.println("error  : " + errorMessage);
            return ResponseEntity.badRequest().build();
        }

        System.out.println(reserveRequest.toString());

        Long reservationId = reserveService.reserveHotelRoom(
                hotelId, roomNumber,
                reserveRequest.getCheckInDate(),
                reserveRequest.getCheckOutDate());

        HotelRoomIdResponse body = HotelRoomIdResponse.from(reservationId);
        return ResponseEntity.ok(body);
    }

}

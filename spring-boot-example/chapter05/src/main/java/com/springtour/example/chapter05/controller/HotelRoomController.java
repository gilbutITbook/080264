package com.springtour.example.chapter05.controller;

import com.springtour.example.chapter05.domain.HotelRoomType;
import com.springtour.example.chapter05.utils.IdGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
public class HotelRoomController {

    private static final String HEADER_CREATED_AT = "X-CREATED-AT";
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");


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

    @DeleteMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
    public DeleteResultResponse deleteHotelRoom(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber) {
        System.out.println("Delete Request. hotelId=" + hotelId + ", roomNumber=" + roomNumber);
        return new DeleteResultResponse(Boolean.TRUE, "success");
    }

    @PutMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}")
    public ResponseEntity<HotelRoomIdResponse> updateHotelRoomByRoomNumber(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            @Valid @RequestBody HotelRoomUpdateRequest hotelRoomUpdateRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = new StringBuilder("validation error.")
                    .append(" field : ").append(fieldError.getField())
                    .append(", code : ").append(fieldError.getCode())
                    .append(", message : ").append(fieldError.getDefaultMessage())
                    .toString();

            System.out.println(errorMessage);
            return ResponseEntity.badRequest().build();
        }

        System.out.println(hotelRoomUpdateRequest.toString());
        HotelRoomIdResponse body = HotelRoomIdResponse.from(1_002_003_004L);
        return ResponseEntity.ok(body);
    }

    @PostMapping(path = "/hotels/{hotelId}/rooms")
    public ResponseEntity<HotelRoomIdResponse> createHotelRoom(
            @PathVariable Long hotelId,
            @RequestBody HotelRoomRequest hotelRoomRequest
    ) {
        System.out.println(hotelRoomRequest.toString());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HEADER_CREATED_AT, DATE_FORMATTER.format(ZonedDateTime.now()));
        HotelRoomIdResponse body = HotelRoomIdResponse.from(1_002_003_004L);

        return new ResponseEntity(body, headers, HttpStatus.OK);
    }
}

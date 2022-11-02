package com.springtour.example.chapter07.controller;

import com.springtour.example.chapter07.domain.HotelRoomEntity;
import lombok.Getter;

@Getter
public class HotelRoomResponse {

    private Long hotelRoomId;
    private String code;
    private Integer floor;
    private Integer bedCount;
    private Integer bathCount;

    private HotelRoomResponse(Long hotelRoomId, String code, Integer floor, Integer bedCount, Integer bathCount) {
        this.hotelRoomId = hotelRoomId;
        this.code = code;
        this.floor = floor;
        this.bedCount = bedCount;
        this.bathCount = bathCount;
    }

    public static HotelRoomResponse from(HotelRoomEntity hotelRoomEntity) {
        return new HotelRoomResponse(hotelRoomEntity.getId(),
                hotelRoomEntity.getCode(),
                hotelRoomEntity.getFloor(),
                hotelRoomEntity.getBedCount(),
                hotelRoomEntity.getBathCount());
    }
}

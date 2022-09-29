package com.springtour.example.chapter07.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HotelRoomEntity {

    private Long id;
    private String code;
    private Integer floor;
    private Integer bedCount;
    private Integer bathCount;

    public HotelRoomEntity(Long id, String code, Integer floor, Integer bedCount, Integer bathCount) {
        this.id = id;
        this.code = code;
        this.floor = floor;
        this.bedCount = bedCount;
        this.bathCount = bathCount;
    }
}

package com.springtour.example.chapter07.repository;

import com.springtour.example.chapter07.domain.HotelRoomEntity;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRoomRepository {

    public HotelRoomEntity findById(Long id) {
        return new HotelRoomEntity(id, "EAST-1902", 19, 2, 1);
    }
}

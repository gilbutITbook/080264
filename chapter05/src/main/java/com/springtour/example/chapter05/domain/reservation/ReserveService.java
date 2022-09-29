package com.springtour.example.chapter05.domain.reservation;

import com.springtour.example.chapter05.domain.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReserveService {

    public Long reserveHotelRoom(Long hotelId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {

        // sudo code. Example
//        hotelRoomRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber)
//                .orElseThrow(() -> {
//                    log.error("Invalid roomNumber. hotelId:{}, roomNumber:{}", hotelId, roomNumber);
//                    return new BadRequestException("Not existing roomNumber");
//                });

        return 1_002_003_004L;
    }
}

package com.springtour.example.chapter10.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Test
    public void test() {
        hotelService.getHotelById(1L);
    }

    @Test
    public void testGetHotelNameAndAddress() {
        hotelService.getHotelNameAndAddress("testHotelName", "testHotelAddress");
    }

    @Test
    public void testGetHotel() {
        HotelRequest request = new HotelRequest(123123L);
        hotelService.getHotel(request);
    }
}

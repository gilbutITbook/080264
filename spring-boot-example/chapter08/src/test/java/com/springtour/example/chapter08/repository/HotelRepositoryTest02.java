package com.springtour.example.chapter08.repository;

import com.springtour.example.chapter08.domain.HotelEntity;
import com.springtour.example.chapter08.domain.HotelStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test-h2.properties")
class HotelRepositoryTest02 {

    private static HotelEntity testHotelEntity;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    public void init() {
        testHotelEntity = HotelEntity.of("The LINE LA", "3515 Wilshire Blvd, Los Angeles, CA 90010", "+12133817411");
    }

    @Test
    public void testFindOne() {
        // Given
        hotelRepository.save(testHotelEntity);

        // When
        HotelEntity hotelEntity = hotelRepository.findOne(testHotelEntity.getHotelId(), HotelStatus.READY);

        // Then
        Assertions.assertEquals(testHotelEntity, hotelEntity);
    }

    @Test
    public void testFindReadyOne() {
        // Given
        hotelRepository.save(testHotelEntity);

        // When
        HotelEntity hotelEntity = hotelRepository.findReadyOne(testHotelEntity.getHotelId());

        // Then
        Assertions.assertEquals(testHotelEntity, hotelEntity);
    }

}

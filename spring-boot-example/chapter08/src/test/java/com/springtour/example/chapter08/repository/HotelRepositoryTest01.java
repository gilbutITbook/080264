package com.springtour.example.chapter08.repository;

import com.springtour.example.chapter08.domain.HotelEntity;
import com.springtour.example.chapter08.domain.HotelStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test-h2.properties")
class HotelRepositoryTest01 {

    private static HotelEntity testHotelEntity;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    public void init() {
        testHotelEntity = HotelEntity.of("The LINE LA", "3515 Wilshire Blvd, Los Angeles, CA 90010", "+12133817411");
    }

    @Test
    public void testFindByStatus() {
        // Given
        hotelRepository.save(testHotelEntity);

        // When
        HotelEntity hotelEntity = hotelRepository.findByStatus(HotelStatus.READY)
                .stream()
                .filter(entity -> entity.getHotelId().equals(testHotelEntity.getHotelId()))
                .findFirst()
                .get();

        // Then
        Assertions.assertNotNull(hotelEntity);
        Assertions.assertEquals(testHotelEntity.getAddress(), hotelEntity.getAddress());
        Assertions.assertEquals(testHotelEntity.getName(), hotelEntity.getName());
        Assertions.assertEquals(testHotelEntity.getPhoneNumber(), hotelEntity.getPhoneNumber());
    }
}

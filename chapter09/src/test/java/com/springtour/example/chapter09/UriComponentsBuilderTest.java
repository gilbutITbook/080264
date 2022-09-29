package com.springtour.example.chapter09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriComponentsBuilderTest {

    @Test
    public void testBuild() {

        URI uri = UriComponentsBuilder.fromPath("/hotel-names/{hotelName}")
                .queryParam("type", "{type}")
                .queryParam("isActive", "{isActive}")
                .scheme("https").host("127.0.0.1").port(18080)
                .build("LineHotel", "Hotel", "true");

        Assertions.assertEquals("https://127.0.0.1:18080/hotel-names/LineHotel?type=Hotel&isActive=true", uri.toString());
    }

    @Test
    public void testEncoding() {
        URI firstUri = UriComponentsBuilder.fromPath("/hotel-names/{hotelName}")
                .scheme("https").host("127.0.0.1").port(18080)
                .build("한국호텔");

        Assertions.assertEquals("https://127.0.0.1:18080/hotel-names/%ED%95%9C%EA%B5%AD%ED%98%B8%ED%85%94", firstUri.toString());

        String variable = "한국호텔";
        String path = "/hotel-names/" + variable;
        URI secondUri = UriComponentsBuilder.fromPath(path)
                .scheme("https").host("127.0.0.1").port(18080)
                .build()
                .toUri();

        Assertions.assertEquals("https://127.0.0.1:18080/hotel-names/한국호텔", secondUri.toString());

        URI thirdUri = UriComponentsBuilder.fromPath(path)
                .scheme("https").host("127.0.0.1").port(18080)
                .build(false).encode()
                .toUri();

        Assertions.assertEquals("https://127.0.0.1:18080/hotel-names/%ED%95%9C%EA%B5%AD%ED%98%B8%ED%85%94", thirdUri.toString());
    }
}

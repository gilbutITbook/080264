package com.springtour.example.chapter10.service;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class HotelKeyGenerator implements KeyGenerator {

    private final String PREFIX = "HOTEL::";

    @Override
    public Object generate(Object target, Method method, Object... params) {

        if (Objects.isNull(params))
            return "NULL";

        return Arrays.stream(params)
                .filter(param -> param instanceof HotelRequest)
                .findFirst()
                .map(obj -> HotelRequest.class.cast(obj))
                .map(hotelRequest -> PREFIX + hotelRequest.getHotelId())
                .orElse(SimpleKeyGenerator.generateKey(params).toString());
    }
}

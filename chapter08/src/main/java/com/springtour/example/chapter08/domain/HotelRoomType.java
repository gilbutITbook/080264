package com.springtour.example.chapter08.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelRoomType {
    SINGLE("single", 0),
    DOUBLE("double", 1),
    TRIPLE("triple", 2),
    QUAD("quad", 3);

    private static final Map<String, HotelRoomType> paramMap = Arrays.stream(HotelRoomType.values())
            .collect(Collectors.toMap(
                    HotelRoomType::getParam,
                    Function.identity()
            ));
    private static final Map<Integer, HotelRoomType> valueMap = Arrays.stream(HotelRoomType.values())
            .collect(Collectors.toMap(
                    HotelRoomType::getValue,
                    Function.identity()
            ));

    private final String param;
    private final Integer value;

    HotelRoomType(String param, Integer value) {
        this.param = param;
        this.value = value;
    }

    @JsonCreator
    public static HotelRoomType fromParam(String param) {
        return Optional.ofNullable(param)
                .map(paramMap::get)
                .orElseThrow(() -> new IllegalArgumentException("param is not valid"));
    }

    public static HotelRoomType fromValue(Integer value) {
        return Optional.ofNullable(value)
                .map(valueMap::get)
                .orElseThrow(() -> new IllegalArgumentException("value is not valid"));
    }

    @JsonValue
    public String getParam() {
        return this.param;
    }

    public Integer getValue() {
        return this.value;
    }
}

package com.springtour.example.chapter10.adapter.cache;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
public class HotelCacheValue {

    private String name;
    private String address;

    private HotelCacheValue(String name, String address) {
        if (Objects.isNull(name))
            throw new IllegalArgumentException("name can't be null");
        if (Objects.isNull(address))
            throw new IllegalArgumentException("address can't be null");

        this.name = name;
        this.address = address;
    }

    @JsonCreator
    public static HotelCacheValue of(@JsonProperty("name") String name,
                                     @JsonProperty("address") String address) {
        return new HotelCacheValue(name, address);
    }
}


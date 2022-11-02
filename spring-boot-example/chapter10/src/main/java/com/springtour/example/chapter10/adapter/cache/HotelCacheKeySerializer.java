package com.springtour.example.chapter10.adapter.cache;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.Objects;

public class HotelCacheKeySerializer implements RedisSerializer<HotelCacheKey> {

    private final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public byte[] serialize(HotelCacheKey hotelCacheKey) throws SerializationException {
        if (Objects.isNull(hotelCacheKey))
            throw new SerializationException("hotelCacheKey is null");

        return hotelCacheKey.toString().getBytes(UTF_8);
    }

    @Override
    public HotelCacheKey deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes))
            throw new SerializationException("bytes is null");

        return HotelCacheKey.fromString(new String(bytes, UTF_8));
    }
}

package com.springtour.example.chapter10.adapter.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.Objects;

@Slf4j
public class HotelCacheValueSerializer implements RedisSerializer<HotelCacheValue> {

    // JSON Mapper
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public byte[] serialize(HotelCacheValue hotelCacheValue) throws SerializationException {
        if (Objects.isNull(hotelCacheValue))
            return null;

        try {
            String json = MAPPER.writeValueAsString(hotelCacheValue);
            return json.getBytes(UTF_8);
        } catch (JsonProcessingException e) {
            throw new SerializationException("json serialize error", e);
        }
    }

    @Override
    public HotelCacheValue deserialize(byte[] bytes) throws SerializationException {

        if (Objects.isNull(bytes))
            return null;

        try {
            return MAPPER.readValue(new String(bytes, UTF_8), HotelCacheValue.class);
        } catch (JsonProcessingException e) {
            throw new SerializationException("json deserialize error", e);
        }
    }
}

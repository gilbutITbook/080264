package com.springtour.example.chapter10.adapter.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Slf4j
public class EventListener implements MessageListener {

    private RedisTemplate<String, String> eventRedisTemplate;
    private RedisSerializer<EventMessage> valueSerializer;

    public EventListener(RedisTemplate<String, String> eventRedisTemplate) {
        this.eventRedisTemplate = eventRedisTemplate;
        this.valueSerializer = (RedisSerializer<EventMessage>) eventRedisTemplate.getValueSerializer();
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        EventMessage eventMessage = valueSerializer.deserialize(message.getBody());

        log.warn("Subscribe Channel : {}", new String(message.getChannel()));
        log.warn("Subscribe Message : {}", eventMessage.toString());
    }
}

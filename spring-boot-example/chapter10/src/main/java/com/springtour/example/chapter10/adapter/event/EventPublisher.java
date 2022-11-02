package com.springtour.example.chapter10.adapter.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher {

    private final RedisTemplate<String, String> eventRedisTemplate;
    private final ChannelTopic eventTopic;

    public EventPublisher(RedisTemplate<String, String> eventRedisTemplate, ChannelTopic eventTopic) {
        this.eventRedisTemplate = eventRedisTemplate;
        this.eventTopic = eventTopic;
    }

    public void sendMessage(EventMessage eventMessage) {
        eventRedisTemplate.convertAndSend(eventTopic.getTopic(), eventMessage);
    }
}

package com.springtour.example.chapter10.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class BiddingConfig {
    @Bean
    public RedisConnectionFactory biddingRedisConnectionFactory() {

        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration("127.0.0.1", 6379);
        return new LettuceConnectionFactory(configuration);
    }

    @Bean(name = "biddingRedisTemplate")
    public RedisTemplate<String, Long> rankRedisTemplate() {
        RedisTemplate<String, Long> rankRedisTemplate = new RedisTemplate<>();
        rankRedisTemplate.setConnectionFactory(biddingRedisConnectionFactory());
        rankRedisTemplate.setKeySerializer(new StringRedisSerializer());
        rankRedisTemplate.setValueSerializer(new GenericToStringSerializer<>(Long.class));

        return rankRedisTemplate;
    }
}

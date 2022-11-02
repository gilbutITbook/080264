package com.springtour.example.chapter06.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ToString
@Getter
@Setter
@ConfigurationProperties(prefix = "springtour.kafka")
public class KafkaProperties {

    private List<String> bootstrapServers;
    private Integer ackLevel;
    private Topic topic;

    @ToString
    @Getter
    @Setter
    public static class Topic {
        private String checkout;
        private String reservation;
    }
}

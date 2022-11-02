package com.springtour.example.chapter12.event.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishUserCreated(Long userId, String emailAddress) {
        UserEvent userEvent = UserEvent.created(this, userId, emailAddress);
        log.info("Publish user created event.");
        applicationEventPublisher.publishEvent(userEvent);
    }
}

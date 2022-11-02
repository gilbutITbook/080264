package com.springtour.example.chapter10.adapter.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class EventTest {

    @Autowired
    private EventPublisher eventPublisher;

    @Test
    public void testPubSub() throws InterruptedException {
        eventPublisher.sendMessage(new EventMessage("test"));
        TimeUnit.SECONDS.sleep(3);
    }
}

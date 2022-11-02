package com.springtour.example.chapter12.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PropagationService {

    public void propagateHotelEvent() {
        log.info("propagation of hotel event");
    }

    public void propagateResourceEvent() {
        log.info("propagation of resource event");
    }
}

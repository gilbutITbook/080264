package com.springtour.example.chapter08.service;

import com.springtour.example.chapter08.domain.HotelEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Slf4j
public class HotelAuditListener {

    @PostPersist
    public void logWhenCreated(HotelEntity hotelEntity){
        log.info("hotel created. id:{}", hotelEntity.getHotelId());
    }

    @PostUpdate
    @PostRemove
    public void logWhenChanged(HotelEntity hotelEntity) {
        log.info("hotel changed. id:{}, name:{}", hotelEntity.getHotelId(), hotelEntity.getName());
    }
}

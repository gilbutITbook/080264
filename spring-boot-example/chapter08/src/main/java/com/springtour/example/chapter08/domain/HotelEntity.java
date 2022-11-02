package com.springtour.example.chapter08.domain;

import com.springtour.example.chapter08.domain.converter.HotelStatusConverter;
import com.springtour.example.chapter08.service.HotelAuditListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
@Entity(name = "hotels")
@Table(name = "hotels", indexes = @Index(name = "INDEX_NAME_STATUS", columnList = "name asc, status asc"))
@EntityListeners(HotelAuditListener.class)
public class HotelEntity extends AbstractManageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "status")
    @Convert(converter = HotelStatusConverter.class)
    private HotelStatus status;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "room_count")
    private Integer roomCount;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotels_hotel_id", referencedColumnName = "hotel_id")
    private List<HotelRoomEntity> hotelRoomEntities;

    public HotelEntity() {
        super();
        this.hotelRoomEntities = new ArrayList<>();
    }

    public static HotelEntity of(String name, String address, String phoneNumber) {
        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.name = name;
        hotelEntity.status = HotelStatus.READY;
        hotelEntity.address = address;
        hotelEntity.phoneNumber = phoneNumber;
        hotelEntity.roomCount = 0;
        return hotelEntity;
    }

    public void addHotelRooms(List<HotelRoomEntity> hotelRoomEntities) {
        this.roomCount += hotelRoomEntities.size();
        this.hotelRoomEntities.addAll(hotelRoomEntities);
    }
}

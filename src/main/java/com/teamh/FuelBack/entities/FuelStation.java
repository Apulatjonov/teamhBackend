package com.teamh.FuelBack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity(name = "fuel_station")
@Getter
@Setter
public class FuelStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "open_time")
    private LocalDateTime openTime;

    @Column(name = "closed_time")
    private LocalDateTime closedTime;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fuelStation")
    private List<FuelEntity> fuels = new java.util.ArrayList<>();
}

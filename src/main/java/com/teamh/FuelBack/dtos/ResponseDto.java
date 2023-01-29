package com.teamh.FuelBack.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private double latitude;
    private double longitude;
    private String fuelType;
    private double price;
    private double amount;
    private String name;
    private Date lastUpdated;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private boolean found;
    private String msg;
    private double distance;
}

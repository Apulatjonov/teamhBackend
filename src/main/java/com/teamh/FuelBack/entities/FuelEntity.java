package com.teamh.FuelBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "fuels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private FuelStation fuelStation;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private double amount;

    @Column(name = "updated_date")
    private Date updatedDate;

}
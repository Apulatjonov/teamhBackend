package com.teamh.FuelBack.repositories;

import com.teamh.FuelBack.entities.FuelEntity;
import com.teamh.FuelBack.entities.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepo extends JpaRepository<FuelEntity, Long> {
    Optional<FuelEntity> findByFuelStationAndName(FuelStation station, String name);
}

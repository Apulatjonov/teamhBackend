package com.teamh.FuelBack.repositories;

import com.teamh.FuelBack.entities.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelStationRepo extends JpaRepository<FuelStation, Long> {
}

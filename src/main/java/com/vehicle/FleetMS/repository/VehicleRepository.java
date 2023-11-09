package com.vehicle.FleetMS.repository;

import com.vehicle.FleetMS.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("from Vehicle where status = 'GARAGE'")
    List<Vehicle> findAllVehiclesInGarage();

    @Query("from Vehicle where status = 'ONLINE'")
    List<Vehicle> findAllAvailableVehicles();
}

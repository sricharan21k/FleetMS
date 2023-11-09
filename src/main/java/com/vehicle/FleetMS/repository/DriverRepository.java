package com.vehicle.FleetMS.repository;

import com.vehicle.FleetMS.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}

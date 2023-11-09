package com.vehicle.FleetMS.repository;

import com.vehicle.FleetMS.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}

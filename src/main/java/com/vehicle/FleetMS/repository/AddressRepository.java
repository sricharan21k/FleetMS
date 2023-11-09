package com.vehicle.FleetMS.repository;

import com.vehicle.FleetMS.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

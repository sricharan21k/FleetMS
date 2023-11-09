package com.vehicle.FleetMS.repository;

import com.vehicle.FleetMS.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

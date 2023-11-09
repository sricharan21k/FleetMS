package com.vehicle.FleetMS.repository;

import com.vehicle.FleetMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

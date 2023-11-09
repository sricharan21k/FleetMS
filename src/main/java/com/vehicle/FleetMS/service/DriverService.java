package com.vehicle.FleetMS.service;

import com.vehicle.FleetMS.model.Driver;
import com.vehicle.FleetMS.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver getDriver(Long id) {
        return driverRepository.findById(id).get();
    }
}

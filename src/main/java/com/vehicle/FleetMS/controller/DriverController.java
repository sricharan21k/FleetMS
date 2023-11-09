package com.vehicle.FleetMS.controller;

import com.vehicle.FleetMS.Enum.VehicleStatus;
import com.vehicle.FleetMS.model.Driver;
import com.vehicle.FleetMS.model.Vehicle;
import com.vehicle.FleetMS.repository.DriverRepository;
import com.vehicle.FleetMS.service.DriverService;
import com.vehicle.FleetMS.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
public class DriverController {
    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/drivers")
    public String drivers(Model model){
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "drivers";
    }

    @GetMapping("/drivers/add")
    public String newDriver(Model model){
        model.addAttribute("driver", new Driver());
        model.addAttribute("vehicles", vehicleService.getVehiclesInGarage());
        return "new-driver";
    }

    @PostMapping("/drivers/add")
    public String addDriver(@ModelAttribute("driver") Driver driver, @RequestParam Long vehicleId,
                            @RequestParam(value = "profileImage", required = false) MultipartFile image){

        if(!image.isEmpty()){
            String fileName = image.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/img/driver/", fileName);
            try {
                Files.write(path, image.getBytes());
                driver.setImage("/img/driver/"+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        driver.setDateOfJoining(LocalDateTime.now());
        Vehicle vehicle = vehicleService.getVehicle(vehicleId).get();
//        driverService.addDriver(driver);
        vehicle.setDriver(driver);
        vehicle.setStatus(VehicleStatus.ONLINE);
        vehicleService.updateVehicle(vehicle);
        return "redirect:/drivers";
    }

    @GetMapping("/drivers/update/{id}")
    public String viewVehicle(@PathVariable Long id, Model model){
        model.addAttribute("driver", driverService.getDriver(id));
        return "update-driver";
    }

    @PostMapping("/drivers/update")
    public String updateVehicle(@ModelAttribute("driver") Driver driver){
        driverService.addDriver(driver);
        return "redirect:/drivers";
    }
}

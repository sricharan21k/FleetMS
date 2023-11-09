package com.vehicle.FleetMS.controller;

import com.vehicle.FleetMS.Enum.VehicleEngineType;
import com.vehicle.FleetMS.Enum.VehicleStatus;
import com.vehicle.FleetMS.Enum.VehicleType;
import com.vehicle.FleetMS.model.Vehicle;
import com.vehicle.FleetMS.repository.UserRepository;
import com.vehicle.FleetMS.repository.VehicleRepository;
import com.vehicle.FleetMS.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @GetMapping("/vehicles")
    public String vehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "vehicles";
    }

    @GetMapping("/vehicles/add")
    public String newVehicleForm(Model model) {
        model.addAttribute("types", VehicleType.values());
        model.addAttribute("engines", VehicleEngineType.values());
        model.addAttribute("vehicle", new Vehicle());
        return "new-vehicle";
    }

    @PostMapping("/vehicles/add")
    public String newVehicle(@ModelAttribute("vehicle") Vehicle vehicle,
                             @RequestParam(value = "imageFile", required = false) MultipartFile image) {
        vehicleService.addVehicle(vehicle, image);
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles/update/{id}")
    public String viewVehicle(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleService.getVehicle(id).get());
        return "update-vehicle";
    }

    @PostMapping("/vehicles/update")
    public String updateVehicle(@ModelAttribute("vehicle") Vehicle vehicle, @RequestParam("imageFile") MultipartFile file) {
        vehicleService.updateVehicleWithImage(vehicle, file);
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

}

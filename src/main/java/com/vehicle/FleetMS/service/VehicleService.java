package com.vehicle.FleetMS.service;

import com.vehicle.FleetMS.Enum.VehicleStatus;
import com.vehicle.FleetMS.model.Vehicle;
import com.vehicle.FleetMS.repository.BookingRepository;
import com.vehicle.FleetMS.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Value("${image.path}")
    private String imagePath;
    @Autowired
    private BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public void addVehicle(Vehicle vehicle, MultipartFile file){
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(imagePath, fileName);
            try {
                Files.write(path, file.getBytes());
                vehicle.setImage("/img/vehicle/"+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicle(Long id){
        return vehicleRepository.findById(id);
    }


    public List<Vehicle> getVehiclesInGarage(){
        return vehicleRepository.findAllVehiclesInGarage();
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAllAvailableVehicles();
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).get();
        if (vehicle.getStatus() != VehicleStatus.GARAGE) return;
        vehicleRepository.deleteById(id);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void updateVehicleWithImage(Vehicle vehicle, MultipartFile file){
        Vehicle existing = vehicleRepository.findById(vehicle.getId()).get();
        if(file.isEmpty()){
            vehicle.setImage(existing.getImage());
        }else {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(imagePath, fileName);
            try {
                Files.write(path, file.getBytes());
                vehicle.setImage("/img/vehicle/"+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        vehicleRepository.save(vehicle);

    }
}

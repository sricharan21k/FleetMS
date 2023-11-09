package com.vehicle.FleetMS.model;

import com.vehicle.FleetMS.Enum.VehicleEngineType;
import com.vehicle.FleetMS.Enum.VehicleStatus;
import com.vehicle.FleetMS.Enum.VehicleType;
import com.vehicle.FleetMS.service.VehicleService;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String make;

    String model;

    @Enumerated(EnumType.STRING)
    VehicleType type;

    @Enumerated(EnumType.STRING)
    VehicleEngineType engine;

    Float rangeValue;

    Float costPerHour;

    @Enumerated(EnumType.STRING)
    VehicleStatus status = VehicleStatus.GARAGE;

    String image;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    Booking booking;

}

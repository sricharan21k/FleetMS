package com.vehicle.FleetMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Long phone;
    private LocalDateTime dateOfJoining;
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;
}

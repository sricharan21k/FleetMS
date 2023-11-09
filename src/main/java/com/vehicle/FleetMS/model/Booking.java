package com.vehicle.FleetMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date bookingDate;

    private LocalDateTime bookingDate;
    private LocalDateTime endDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "booking")
    private Vehicle vehicle;

    @ManyToOne
    private Customer customer;

    @Transient
    private Float totalFare;


}

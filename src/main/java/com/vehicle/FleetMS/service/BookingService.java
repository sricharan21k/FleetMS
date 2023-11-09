package com.vehicle.FleetMS.service;

import com.vehicle.FleetMS.model.Booking;
import com.vehicle.FleetMS.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }



}

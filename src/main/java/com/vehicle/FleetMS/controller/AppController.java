package com.vehicle.FleetMS.controller;

import com.vehicle.FleetMS.model.Booking;
import com.vehicle.FleetMS.model.Customer;
import com.vehicle.FleetMS.model.Vehicle;
import com.vehicle.FleetMS.service.BookingService;
import com.vehicle.FleetMS.service.CustomerService;
import com.vehicle.FleetMS.service.DriverService;
import com.vehicle.FleetMS.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@Controller
public class AppController {
    private final VehicleService vehicleService;
    private final BookingService bookingService;
    private final CustomerService customerService;

    public AppController(VehicleService vehicleService, BookingService bookingService, CustomerService customerService) {
        this.vehicleService = vehicleService;
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Booking> bookings = bookingService.getBookings();
        updateBookingTotalFare(bookings);
        List<Customer> customers = customerService.getAllCustomers();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        int bookingCount = bookings.size();
        int customerCount = customers.size();
        int vehicleCount = vehicles.size();

        model.addAttribute("customers", customerCount > 0 ? customerCount : "NA");
        model.addAttribute("bookingCount", bookingCount > 0 ? bookingCount : "NA");
        model.addAttribute("vehicleCount", vehicleCount > 0 ? vehicleCount : "0.0");
        model.addAttribute("vehicles", vehicleCount > 2 ? vehicles.reversed().subList(0,3) : vehicles);
        model.addAttribute("bookings", bookings);
//        model.addAttribute("popularModel", );
        if(bookingCount > 0){
            double revenue = calculateTotalRevenue(bookings);
            model.addAttribute("revenue", revenue);
        }else {
            model.addAttribute("revenue", "0.0");
        }

        return "index";
    }


    @GetMapping("/bookings")
    public String bookings(Model model) {
        List<Booking> bookings = bookingService.getBookings();
        bookings.sort(Comparator.comparing(Booking::getBookingDate).reversed());
        updateBookingTotalFare(bookings);
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    private void updateBookingTotalFare(List<Booking> bookings) {
        bookings.forEach(booking -> {
            Duration duration = Duration.between(booking.getBookingDate(), booking.getEndDate());
            long hours = duration.toHours();
            booking.setTotalFare(booking.getVehicle().getCostPerHour() * hours);
        });
    }

    private Double calculateTotalRevenue(List<Booking> bookings){
        return bookings.stream().mapToDouble(Booking::getTotalFare).sum();
    }
}
